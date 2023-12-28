package com.HiBro.controller;

import com.HiBro.constant.*;
import com.HiBro.entity.Screen;
import com.HiBro.service.ScreenService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class ScreenController {
	private final ScreenService screenService;

	@GetMapping("/screen")
	public String getScreen(@RequestParam(name = "location", required = false) Location location,
							@RequestParam(name = "type", required = false) ScreenType type,
							Model model, Optional<Integer> page,
							HttpServletRequest request) {
		Pageable pageable = PageRequest.of(page.map(integer -> integer - 1).orElse(0), 10);
		model.addAttribute("maxPage", 5);
		Page<Screen> screenList;

		String url = (request.getQueryString() != null) ?
				(request.getQueryString().contains("location") && !request.getQueryString().contains("page")) ?
						request.getQueryString() + "&" :
						request.getQueryString().split("page")[0] :
				"";
		model.addAttribute("url", "?" + url);

		if (type != null && location == null) {
			screenList = screenService.findByType(type, pageable);
		} else if (location != null && type == null) {
			screenList = screenService.findByTheaterLocation(location, pageable);
		} else if (type != null && location != null) {
			screenList = screenService.findByScreenTypeAndTheaterLocation(type, location, pageable);
		} else {
			screenList = screenService.findAll(pageable);
		}

		model.addAttribute("screenList", screenList);
		return "special_screen";
	}
}
