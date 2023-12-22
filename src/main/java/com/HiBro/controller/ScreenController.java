package com.HiBro.controller;

import com.HiBro.constant.*;
import com.HiBro.entity.Screen;
import com.HiBro.service.ScreenService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class ScreenController {
	private final ScreenService screenService;

	@GetMapping("/screen")
	public String getScreen(@RequestParam(name = "location", required = false) Location location,
							@RequestParam(name = "screenType", required = false) ScreenType screenType,
							Model model, Optional<Integer> page) {
		Pageable pageable = PageRequest.of(page.orElse(0), 10);
		model.addAttribute("maxPage", 5);
		Page<Screen> screenList;

		if (screenType != null && location == null) {
			screenList = screenService.findByScreenType(screenType, pageable);
		} else if (location != null && screenType == null) {
			screenList = screenService.findByTheaterLocation(location, pageable);
		} else if (screenType != null && location != null) {
			screenList = screenService.findByScreenTypeAndTheaterLocation(screenType, location, pageable);
		} else {
			screenList = screenService.findAll(pageable);
		}

		model.addAttribute("screenList", screenList);
		return "special_screen";
	}
}
