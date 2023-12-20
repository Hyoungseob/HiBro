package com.HiBro.controller;

import com.HiBro.entity.Screen;
import com.HiBro.repository.ScreenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
public class ScreenController {

	@Autowired
	ScreenRepository screenRepository;

	@GetMapping("/screen")
	public String getScreen(Model model, Optional<Integer> page) {
		Pageable pageable = PageRequest.of(page.isPresent() ? page.get() : 0, 10);
		Page<Screen> screenList = screenRepository.findAll(pageable);
		model.addAttribute("maxPage", 5);
		model.addAttribute("screenList" , screenList);
		return "special_screen";
	}

	@PostMapping("/screen")
	public String getTheaters(Model model, @RequestParam("screenType") String screenType) {
		List<Screen> theaterAndScreenList = screenRepository.findByScreenTypeContaining(screenType);
		model.addAttribute("theaterAndScreenList", theaterAndScreenList);
		return "special_screen";
	}
}
