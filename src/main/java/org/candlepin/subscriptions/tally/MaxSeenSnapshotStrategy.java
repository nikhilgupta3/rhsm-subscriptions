/*
 * Copyright (c) 2021 Red Hat, Inc.
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
package org.candlepin.subscriptions.tally;

import org.candlepin.subscriptions.db.TallySnapshotRepository;
import org.candlepin.subscriptions.db.model.TallySnapshot;
import org.candlepin.subscriptions.files.ProductProfileRegistry;
import org.candlepin.subscriptions.tally.roller.BaseSnapshotRoller;
import org.candlepin.subscriptions.tally.roller.DailySnapshotRoller;
import org.candlepin.subscriptions.tally.roller.HourlySnapshotRoller;
import org.candlepin.subscriptions.tally.roller.MonthlySnapshotRoller;
import org.candlepin.subscriptions.tally.roller.QuarterlySnapshotRoller;
import org.candlepin.subscriptions.tally.roller.WeeklySnapshotRoller;
import org.candlepin.subscriptions.tally.roller.YearlySnapshotRoller;
import org.candlepin.subscriptions.util.ApplicationClock;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Strategy for producing snapshots that captures the largest value recorded.
 */
@Service
public class MaxSeenSnapshotStrategy {

    private static final Logger log = LoggerFactory.getLogger(MaxSeenSnapshotStrategy.class);

    private final HourlySnapshotRoller hourlyRoller;
    private final DailySnapshotRoller dailyRoller;
    private final WeeklySnapshotRoller weeklyRoller;
    private final MonthlySnapshotRoller monthlyRoller;
    private final YearlySnapshotRoller yearlyRoller;
    private final QuarterlySnapshotRoller quarterlyRoller;
    private final SnapshotSummaryProducer summaryProducer;

    @Autowired
    public MaxSeenSnapshotStrategy(TallySnapshotRepository tallyRepo, ApplicationClock clock,
        ProductProfileRegistry registry, SnapshotSummaryProducer summaryProducer) {
        this.summaryProducer = summaryProducer;
        hourlyRoller = new HourlySnapshotRoller(tallyRepo, clock, registry);
        dailyRoller = new DailySnapshotRoller(tallyRepo, clock, registry);
        weeklyRoller = new WeeklySnapshotRoller(tallyRepo, clock, registry);
        monthlyRoller = new MonthlySnapshotRoller(tallyRepo, clock, registry);
        yearlyRoller = new YearlySnapshotRoller(tallyRepo, clock, registry);
        quarterlyRoller = new QuarterlySnapshotRoller(tallyRepo, clock, registry);
    }

    @Transactional
    public Map<String, List<TallySnapshot>> produceSnapshotsFromCalculations(Collection<String> accounts,
        Collection<AccountUsageCalculation> accountCalcs) {
        Stream<BaseSnapshotRoller> rollers = Stream.of(hourlyRoller, dailyRoller, weeklyRoller, monthlyRoller,
            quarterlyRoller, yearlyRoller);
        var newAndUpdatedSnapshots = rollers
            .map(roller -> roller.rollSnapshots(accounts, accountCalcs))
            .flatMap(Collection::stream)
            .collect(Collectors.groupingBy(TallySnapshot::getAccountNumber));
        summaryProducer.produceTallySummaryMessages(newAndUpdatedSnapshots);
        log.info("Finished producing snapshots for all accounts.");
        return newAndUpdatedSnapshots;
    }
}
