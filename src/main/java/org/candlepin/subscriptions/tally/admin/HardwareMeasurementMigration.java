/*
 * Copyright Red Hat, Inc.
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <https://www.gnu.org/licenses/>.
 *
 * Red Hat trademarks are not licensed under GPLv3. No permission is
 * granted to use or replicate Red Hat trademarks that are incorporated
 * in this software or its documentation.
 */
package org.candlepin.subscriptions.tally.admin;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.SqlRowSetResultSetExtractor;
import org.springframework.jdbc.support.rowset.SqlRowSet;

@Slf4j
public class HardwareMeasurementMigration extends DataMigration {

  public static final SqlRowSetResultSetExtractor SQL_ROW_SET_RESULT_SET_EXTRACTOR =
      new SqlRowSetResultSetExtractor();

  public static final String INSERT_SQL =
      "insert into tally_measurements(snapshot_id, measurement_type, uom, value)\n"
          + "values (?, ?, ?, ?)\n"
          + "on conflict(snapshot_id, measurement_type, uom)\n"
          + "   do update set value=excluded.value\n"
          + "   where tally_measurements.value != excluded.value";

  private static final String HARDWARE_MEASUREMENT_QUERY =
      "select h.snapshot_id, h.measurement_type, sockets, cores\n"
          + "from hardware_measurements h\n"
          + "where ?::uuid is null\n"
          + "   or h.snapshot_id > ?::uuid\n"
          + "order by h.snapshot_id\n"
          + "limit ?";

  private final Counter counter;

  public HardwareMeasurementMigration(JdbcTemplate jdbcTemplate, MeterRegistry meterRegistry) {
    super(jdbcTemplate, meterRegistry);
    counter = meterRegistry.counter("swatch_hardware_measurement_migration");
  }

  @Override
  public SqlRowSet extract(String recordOffset, int batchSize) {
    return jdbcTemplate.query(
        HARDWARE_MEASUREMENT_QUERY,
        new Object[] {recordOffset, recordOffset, batchSize},
        new int[] {Types.VARCHAR, Types.VARCHAR, Types.NUMERIC},
        SQL_ROW_SET_RESULT_SET_EXTRACTOR);
  }

  @Override
  public String transformAndLoad(SqlRowSet data) {
    String lastSeenSnapshotId = null;
    List<Object[]> insertList = new ArrayList<>();
    int snapshotCount = 0;
    while (data.next()) {
      String snapshotId = data.getString("snapshot_id");
      lastSeenSnapshotId = snapshotId;
      String measurementType = data.getString("measurement_type");
      Double sockets = extractNullableDouble(data, "sockets");
      Double cores = extractNullableDouble(data, "cores");

      log.debug("Inserting sockets for tally snapshotId: {}", snapshotId);
      insertList.add(new Object[] {snapshotId, measurementType, "SOCKETS", sockets});

      log.debug("Inserting cores for tally snapshotId: {}", snapshotId);
      insertList.add(new Object[] {snapshotId, measurementType, "CORES", cores});

      snapshotCount++;
    }

    jdbcTemplate.batchUpdate(INSERT_SQL, insertList);
    counter.increment(snapshotCount);
    return lastSeenSnapshotId;
  }

  @Override
  @SuppressWarnings("java:S1192") // supress duplicate string warnings
  public void recordCompleted() {
    markLiquibaseChangesetRan(
        jdbcTemplate,
        Map.of(
            "id",
            "202208251616-3",
            "author",
            "khowell",
            "filename",
            "liquibase/202208251616-migrate-cores-sockets-data.xml",
            "md5sum",
            "8:098818ac2df540a55e17763bf64006c3"));
    markLiquibaseChangesetRan(
        jdbcTemplate,
        Map.of(
            "id",
            "202208251616-4",
            "author",
            "khowell",
            "filename",
            "liquibase/202208251616-migrate-cores-sockets-data.xml",
            "md5sum",
            "8:5e353189c216fb19646ef4183c28488a"));
  }

  private Double extractNullableDouble(SqlRowSet data, String column) {
    double value = data.getDouble(column);
    if (data.wasNull()) {
      return null;
    }
    return value;
  }
}
