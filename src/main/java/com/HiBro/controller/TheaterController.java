package com.HiBro.controller;

import com.HiBro.entity.Theater;
import com.HiBro.service.TheaterService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class TheaterController {
	private final TheaterService theaterService;

	@GetMapping("/theater")
	public String theater(Model model) {
		List<Theater> theaterList = theaterService.findAll();
		model.addAttribute("theaterList", theaterList);
		return "ticketing";
	}
}
