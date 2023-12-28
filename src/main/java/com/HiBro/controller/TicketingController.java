package com.HiBro.controller;

import com.HiBro.entity.Movie;
import com.HiBro.entity.ScreenDate;
import com.HiBro.entity.Theater;
import com.HiBro.service.MovieService;
import com.HiBro.service.ScreenDateService;
import com.HiBro.service.TheaterService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class TicketingController {
	private final TheaterService theaterService;
	private final MovieService movieService;
	private final ScreenDateService screenDateService;

	@GetMapping("/ticketing")
	public String getTicketing(Long screenCode, Long movieCode, Model model) {
		List<Theater> theaterList = theaterService.findAll();
		List<Movie> movieList = movieService.findAll();
		List<ScreenDate> screenDateList = screenDateService.findByScreenCodeAndMovieCode(screenCode, movieCode);
		model.addAttribute("theaterList", theaterList);
		model.addAttribute(movieList);
		model.addAttribute(screenDateList);
		return "ticketing/ticketing";
	}

	@PostMapping("/ticketing")
	public ResponseEntity getScreenDate(@RequestBody Long movieCode, @RequestBody Long screenCode){
		return new ResponseEntity(HttpStatus.OK);
	}
}
