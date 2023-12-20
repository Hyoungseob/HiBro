package com.HiBro.controller;

import com.HiBro.entity.Theater;
import com.HiBro.repository.TheaterRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class TheaterController {
	private final TheaterRepository theaterRepository;

	@GetMapping("/theater")
	public String theater(Model model) {
		List<Theater> theaterList = theaterRepository.findAll();
		model.addAttribute("theaterList", theaterList);
		return "ticketing";
	}
}
