package com.HiBro.controller;

import com.HiBro.constant.Location;
import com.HiBro.constant.ScreenType;
import com.HiBro.entity.Screen;
import com.HiBro.entity.Theater;
import com.HiBro.repository.ScreenRepository;
import com.HiBro.repository.TheaterRepository;
import com.HiBro.specification.ScreenSpecification;
import com.HiBro.specification.TheaterSpecification;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.thymeleaf.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class ScreenController {
	private final ScreenRepository screenRepository;
	private final TheaterRepository theaterRepository;

	@ModelAttribute("screenTypes")
	public ScreenType[] screenTypes() {
		return ScreenType.values();
	}

	@GetMapping("/screen")
	public String getScreen(@RequestParam(name = "location", required = false) String location,
							@RequestParam(name = "screenType", required = false) ScreenType screenType,
							Model model, Optional<Integer> page) {
		Specification<Screen> screenSpecification = Specification.where(null);
		Specification<Theater> theaterSpecification = Specification.where(null);
		Pageable pageable = PageRequest.of(page.isPresent() ? page.get() : 0, 10);
		model.addAttribute("maxPage", 5);
		if (screenType != null && location.isEmpty()) {
			screenSpecification = screenSpecification.and(ScreenSpecification.categoryEquals(screenType));
			Page<Screen> findScreenListByScreenType = screenRepository.findAll(screenSpecification, pageable);
			model.addAttribute("screenList", findScreenListByScreenType);
		} else if (!StringUtils.isEmpty(location) && screenType == null) {
			Location locationEnum = Location.valueOf(location);
			theaterSpecification = theaterSpecification.and(TheaterSpecification.categoryEquals(locationEnum));
			Page<Theater> findScreenListByLocation = theaterRepository.findAll(theaterSpecification, pageable);
			List<Screen> screenList = new ArrayList<>();
			for (Theater theater : findScreenListByLocation.getContent()) {
				for (Screen screen : theater.getScreens()) {
					screenList.add(screen);
				}
			}
			model.addAttribute("screenList", new PageImpl<>(screenList, pageable, screenList.size()));
		} else if (screenType != null && !StringUtils.isEmpty(location)) {
			screenSpecification = screenSpecification.and(ScreenSpecification.categoryEquals(screenType));
			Page<Screen> findScreenListByScreenType = screenRepository.findAll(screenSpecification, pageable);

			Location locationEnum = Location.valueOf(location);
			theaterSpecification = theaterSpecification.and(TheaterSpecification.categoryEquals(locationEnum));
			Page<Theater> findScreenListByLocation = theaterRepository.findAll(theaterSpecification, pageable);

			List<Screen> screenList = new ArrayList<>();

			for (Theater theater : findScreenListByLocation.getContent()) {
				for (Screen screenTheater : theater.getScreens()) {
					for (Screen screen : findScreenListByScreenType.getContent()) {
						if (screenTheater == screen) {
							screenList.add(screen);
						}
					}
				}
			}
			model.addAttribute("screenList", new PageImpl<>(screenList, pageable, screenList.size()));
		} else {
			Page<Screen> screenList = screenRepository.findAll(pageable);
			model.addAttribute("screenList", screenList);
		}
		return "special_screen";

	}
}
