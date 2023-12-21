package com.HiBro.specification;

import com.HiBro.constant.ScreenType;
import com.HiBro.entity.Screen;
import org.springframework.data.jpa.domain.Specification;

public class ScreenSpecification {
	public static Specification<Screen> categoryEquals(ScreenType screenType) {
		return (root, query, criteriaBuilder) ->
				criteriaBuilder.equal(root.get("screenType"), screenType);
	}
}
