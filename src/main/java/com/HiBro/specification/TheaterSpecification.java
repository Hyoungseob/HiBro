package com.HiBro.specification;

import com.HiBro.constant.Location;
import com.HiBro.entity.Theater;
import org.springframework.data.jpa.domain.Specification;

public class TheaterSpecification {
	public static Specification<Theater> categoryEquals(Location location) {
		return (root, query, criteriaBuilder) ->
				criteriaBuilder.equal(root.get("location"), location);
	}
}
