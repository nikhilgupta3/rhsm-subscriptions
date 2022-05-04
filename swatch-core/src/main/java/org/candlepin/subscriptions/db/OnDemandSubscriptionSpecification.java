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
package org.candlepin.subscriptions.db;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.extern.slf4j.Slf4j;
import org.candlepin.subscriptions.db.model.Offering;
import org.candlepin.subscriptions.db.model.Offering_;
import org.candlepin.subscriptions.db.model.Subscription;
import org.candlepin.subscriptions.db.model.Subscription_;
import org.springframework.data.jpa.domain.Specification;

@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Slf4j
public class OnDemandSubscriptionSpecification
    implements Specification<Subscription> {

  private final transient List<SearchCriteria> criteria;

  @Override
  public Predicate toPredicate(
      Root<Subscription> root, CriteriaQuery<?> query, CriteriaBuilder builder) {

    List<Predicate> predicates = new ArrayList<>();

    Root<Offering> offeringRoot = query.from(Offering.class);

    predicates.add(builder.equal(offeringRoot.get(Offering_.sku), root.get(Subscription_.sku)));

        criteria.stream()
            .map(c -> mapSingleSearchCriteriaToPredicate(root, offeringRoot, c, builder))
            .filter(Objects::nonNull)
            .forEach(predicate -> predicates.add(predicate));

    return builder.and(predicates.toArray(Predicate[]::new));
  }

  private Predicate mapSingleSearchCriteriaToPredicate(
      Root<Subscription> root, Root<Offering> offeringRoot, SearchCriteria criteria, CriteriaBuilder builder) {

    Path<?> expression = root;

    if (Offering_.productName.equals(criteria.getKey())) {
      return builder.equal(offeringRoot.get(Offering_.productName), criteria.getValue());
    } else if (Offering_.USAGE.equals(criteria.getKey())) {
      return builder.equal(offeringRoot.get(Offering_.usage), criteria.getValue());
    } else if (Offering_.SERVICE_LEVEL.equals(criteria.getKey())) {
      return builder.equal(offeringRoot.get(Offering_.serviceLevel), criteria.getValue());
    } else if (criteria.equals(SearchOperation.GREATER_THAN_EQUAL)) {
      return builder.greaterThanOrEqualTo(
          expression.get(criteria.getKey()), criteria.getValue().toString());
    } else if (criteria.getOperation().equals(SearchOperation.LESS_THAN_EQUAL)) {
      return builder.lessThanOrEqualTo(
          expression.get(criteria.getKey()), criteria.getValue().toString());
    } else if (criteria.getOperation().equals(SearchOperation.AFTER_OR_ON)) {
      return builder.greaterThanOrEqualTo(
          expression.get(criteria.getKey()), (OffsetDateTime) criteria.getValue());
    } else if (criteria.getOperation().equals(SearchOperation.BEFORE_OR_ON)) {
      return builder.lessThanOrEqualTo(
          expression.get(criteria.getKey()), (OffsetDateTime) criteria.getValue());
    } else if (criteria.getOperation().equals(SearchOperation.IN)) {
      return builder.in(expression.get(criteria.getKey())).value(criteria.getValue());
    } else if (criteria.getOperation().equals(SearchOperation.NOT_IN)) {
      return builder.in(expression.get(criteria.getKey())).value(criteria.getValue()).not();
    } else {
      return builder.equal(expression.get(criteria.getKey()), criteria.getValue());
    }
  }
}
