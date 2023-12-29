package com.HiBro.controller;

import com.HiBro.entity.*;
import com.HiBro.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@Controller
@RequiredArgsConstructor
public class TicketingController {
	private final TheaterService theaterService;
	private final MovieService movieService;
	private final ScreenDateService screenDateService;
	private final ScreenService screenService;

	@GetMapping("/ticketing")
	public String getTicketing(Model model) {
		List<Theater> theaterList = theaterService.findAll();
		List<Movie> movieList = movieService.findAll();
		model.addAttribute("theaterList", theaterList);
		model.addAttribute(movieList);
		return "ticketing/ticketing";
	}

	@PostMapping("/ticketing")
	public @ResponseBody ResponseEntity<List<ScreenDate>> getScreenDate(@RequestParam Long movieCode, @RequestParam Long theaterCode, Model model) {
		List<Screen> screenList = screenService.getScreenList(theaterCode);
		List<ScreenDate> screenDateList = new ArrayList<>();
		for (Screen screen : screenList) {
			for (ScreenDate screenDate : screenDateService.findByScreenCodeAndMovieCode(screen.getCode(), movieCode)) {
				screenDateList.add(screenDate);
			}
		}
		model.addAttribute("screenDateList", screenDateList);
		return new ResponseEntity(screenDateList, HttpStatus.OK);
	}

	@GetMapping("/seat")
	public String getSeat() {
		return "ticketing/seat";
	}
}
