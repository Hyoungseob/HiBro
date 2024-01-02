package com.HiBro.controller;

import com.HiBro.entity.*;
import com.HiBro.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
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
		LocalDateTime currentDateTime = LocalDateTime.now();
		LocalDateTime endDate = currentDateTime.plusDays(10);

		List<LocalDate> dates = new ArrayList<>();

		while (!currentDateTime.isAfter(endDate)) {
			dates.add(currentDateTime.toLocalDate());
			currentDateTime = currentDateTime.plusDays(1);
		}

		model.addAttribute("dates", dates);
		model.addAttribute("theaterList", theaterList);
		model.addAttribute(movieList);
		return "ticketing/ticketing";
	}

	@PostMapping("/ticketing")
	public @ResponseBody ResponseEntity<List<ScreenDate>> getScreenDate(@RequestParam Long movieCode,
																		@RequestParam Long theaterCode,
																		@RequestParam @DateTimeFormat(pattern = "yyyyMMdd") LocalDate selectedDate,
																		Model model) {
		LocalDateTime startDateTime = LocalDateTime.of(selectedDate, LocalTime.MIN);
		LocalDateTime endDateTime = LocalDateTime.of(selectedDate, LocalTime.MAX);
		List<Screen> screenList = screenService.getScreenList(theaterCode);
		List<ScreenDate> screenDateList = new ArrayList<>();
		for (Screen screen : screenList) {
			screenDateList.addAll(screenDateService.findByScreenCodeAndMovieCodeAndScreeningDateTimeBetweenOrderByScreeningDateTimeAsc(screen.getCode(), movieCode, startDateTime, endDateTime));
		}
		model.addAttribute("screenDateList", screenDateList);
		return new ResponseEntity(screenDateList, HttpStatus.OK);
	}

	@GetMapping("/seat")
	public String getSeat() {
		return "ticketing/seat";
	}

	@PostMapping("/seat")
	public @ResponseBody ResponseEntity<List<Seat>> getSeat(@RequestParam String localDateTime, Model model) {


		return new ResponseEntity<>(HttpStatus.OK);
	}
}
