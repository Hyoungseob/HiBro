package com.HiBro.controller;

import com.HiBro.constant.*;
import com.HiBro.dto.*;
import com.HiBro.entity.*;
import com.HiBro.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.http.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@Controller
@RequiredArgsConstructor
public class AdminTheaterController {
	private final TheaterService theaterService;
	private final ScreenService screenService;
	private final ScreenDateService screenDateService;
	private final SeatService seatService;
	private final MovieService movieService;

	@GetMapping("/admin/theater")
	public String getTheater(Model model, Optional<Integer> page) {
		Pageable pageable = PageRequest.of(page.isPresent() ? page.get() : 0, 10);
		Page<Theater> theaterList = theaterService.getTheaterList(pageable);
		model.addAttribute("maxPage", 5);
		model.addAttribute("theaterList", theaterList);
		return "administrator/admin_theater";
	}

	@GetMapping("/admin/theater/new")
	public String theaterForm(Model model) {
		model.addAttribute("theaterDTO", new TheaterDTO());
		return "administrator/admin_theater_form";
	}

	@PostMapping("/admin/theater/new")
	public String theaterForm(TheaterDTO theaterDTO, BindingResult bindingResult, Model model) {
		if (bindingResult.hasErrors()) {
			return "administrator/admin_theater_form";
		}
		try {
			theaterService.saveTheater(theaterDTO);
		} catch (IllegalStateException e) {
			model.addAttribute("errorMessage", e.getMessage());
			return "administrator/admin_theater_form";
		}
		return "redirect:/admin/theater";
	}

	@GetMapping("/admin/theater/{theaterCode}/update")
	public String updateTheater(@PathVariable("theaterCode") Long theaterCode, Model model) {
		Theater theater = theaterService.getTheater(theaterCode);
		TheaterDTO theaterDTO = TheaterDTO.of(theater);
		model.addAttribute(theaterDTO);
		return "/administrator/admin_theater_form";
	}

	@PostMapping("/admin/theater/{theaterCode}")
	public String updateTheater(Long theaterCode, TheaterDTO theaterDTO) {
		theaterDTO.setCode(theaterCode);
		theaterService.updateTheater(theaterDTO);
		return "redirect:/admin/theater";
	}

	@DeleteMapping("/admin/theater/{theaterCode}")
	public @ResponseBody ResponseEntity deleteTheater(@PathVariable("theaterCode") Long theaterCode) {
		theaterService.deleteTheater(theaterCode);
		return new ResponseEntity(theaterCode, HttpStatus.OK);
	}

	@GetMapping("/admin/theater/{theaterCode}")
	public String getScreen(@PathVariable("theaterCode") Long theaterCode, Model model, Optional<Integer> page) {
		Pageable pageable = PageRequest.of(page.isPresent() ? page.get() : 0, 10);
		model.addAttribute("maxPage", 5);
		Page<Screen> screenList = screenService.getScreenList(theaterCode, pageable);
		Theater theater = theaterService.getTheater(theaterCode);
		model.addAttribute(theater);
		model.addAttribute("screenList", screenList);
		return "administrator/admin_screen";
	}

	@GetMapping("/admin/theater/{theaterCode}/new")
	public String screenForm(@PathVariable("theaterCode") Long theaterCode, Model model) {
		model.addAttribute("screenDTO", new ScreenDTO());
		model.addAttribute("theaterCode", theaterCode);
		return "administrator/admin_screen_form";
	}

	@PostMapping("/admin/theater/{theaterCode}/new")
	public String screenForm(Long theaterCode, ScreenDTO screenDTO, BindingResult bindingResult, Model model) {
		if (bindingResult.hasErrors()) {
			return "administrator/admin_screen_form";
		}
		try {
			screenService.saveScreen(screenDTO, theaterCode);
		} catch (IllegalStateException e) {
			model.addAttribute("errorMessage", e.getMessage());
			return "administrator/admin_screen_form";
		}
		return "redirect:/admin/theater/" + theaterCode;
	}

	@GetMapping("/admin/screen/{screenCode}/update")
	public String updateScreen(@PathVariable("screenCode") Long screenCode, Model model) {
		Screen screen = screenService.getScreen(screenCode);
		ScreenDTO screenDTO = ScreenDTO.of(screen);
		model.addAttribute(screenDTO);
		return "/administrator/admin_screen_form";
	}

	@PostMapping("/admin/screen/{screenCode}")
	public String updateScreen(Long screenCode, ScreenDTO screenDTO) {
		Screen screen = screenService.getScreen(screenCode);
		screenDTO.setCode(screenCode);
		screenService.updateScreen(screenDTO);
		return "redirect:/admin/theater/" + screen.getTheater().getCode();
	}

	@DeleteMapping("/admin/screen/{screenCode}")
	public @ResponseBody ResponseEntity deleteScreen(@PathVariable("screenCode") Long screenCode) {
		screenService.deleteScreen(screenCode);
		return new ResponseEntity(screenCode, HttpStatus.OK);
	}

	@GetMapping("/admin/screen/{screenCode}")
	public String getScreenDate(@PathVariable("screenCode") Long screenCode, Model model, Optional<Integer> page) {
		Pageable pageable = PageRequest.of(page.isPresent() ? page.get() : 0, 10);
		model.addAttribute("maxPage", 5);
		Page<ScreenDate> screenDateList = screenDateService.getScreenDateList(screenCode, pageable);
		Screen screen = screenService.getScreen(screenCode);
		Theater theater = screen.getTheater();
		model.addAttribute("screenDateList", screenDateList);
		model.addAttribute(screen);
		model.addAttribute(theater);
		return "administrator/admin_screenDate";
	}

	@GetMapping("/admin/screen/{screenCode}/new")
	public String screenDateForm(@PathVariable("screenCode") Long screenCode, Model model) {
		List<Movie> movieList = movieService.findAll();

		model.addAttribute("movieList", movieList);
		model.addAttribute("screenDateDTO", new ScreenDateDTO());
		model.addAttribute("screenCode", screenCode);
		model.addAttribute("screeningTime", ScreeningTime.values());

		return "administrator/admin_screenDate_form";
	}

	@PostMapping("/admin/screen/{screenCode}/new")
	public String screenDateForm(Long screenCode, ScreenDateDTO screenDateDTO, BindingResult bindingResult, Model model, Long movieCode) {
		if (bindingResult.hasErrors()) {
			return "administrator/admin_screenDate_form";
		}
		try {
			Movie movie = movieService.findByCode(movieCode);
			screenDateService.saveScreenDate(screenDateDTO, screenCode, movie);
		} catch (IllegalStateException e) {
			model.addAttribute("errorMessage", e.getMessage());
			return "administrator/admin_screenDate_form";
		}
		return "redirect:/admin/screen/" + screenCode;
	}

	@GetMapping("/admin/screenDate/{screenDateCode}/update")
	public String updateScreenDate(@PathVariable("screenDateCode") Long screenDateCode, Model model) {
		List<Movie> movieList = movieService.findAll();

		model.addAttribute("movieList", movieList);
		ScreenDate screenDate = screenDateService.getScreenDate(screenDateCode);
		ScreenDateDTO screenDateDTO = ScreenDateDTO.of(screenDate);
		model.addAttribute(screenDateDTO);
		return "/administrator/admin_screenDate_form";
	}

	@PostMapping("/admin/screenDate/{screenDateCode}")
	public String updateScreenDate(Long screenDateCode, ScreenDateDTO screenDateDTO, Long code) {
		ScreenDate screenDate = screenDateService.getScreenDate(screenDateCode);
		Movie movie = movieService.findByCode(code);
		screenDateDTO.setCode(screenDateCode);
		screenDateDTO.setMovie(movie);
		screenDateService.updateScreenDate(screenDateDTO);
		return "redirect:/admin/screen/" + screenDate.getScreen().getCode();
	}

	@DeleteMapping("/admin/screenDate/{screenDateCode}")
	public @ResponseBody ResponseEntity deleteScreenDate(@PathVariable("screenDateCode") Long screenDateCode) {
		screenDateService.deleteScreenDate(screenDateCode);
		return new ResponseEntity(screenDateCode, HttpStatus.OK);
	}

	@GetMapping("/admin/screenDate/{screenDateCode}")
	public String getSeat(@PathVariable("screenDateCode") Long screenDateCode, Model model, Optional<Integer> page) {
		Pageable pageable = PageRequest.of(page.isPresent() ? page.get() : 0, 10);
		model.addAttribute("maxPage", 5);
		Page<Seat> seatList = seatService.getSeatList(screenDateCode, pageable);
		ScreenDate screenDate = screenDateService.getScreenDate(screenDateCode);
		Screen screen = screenDate.getScreen();
		Theater theater = screen.getTheater();
		model.addAttribute(theater);
		model.addAttribute(screen);
		model.addAttribute(screenDate);
		model.addAttribute("seatList", seatList);
		return "administrator/admin_seat";
	}

	@GetMapping("/admin/screenDate/{screenDateCode}/new")
	public String seatForm(@PathVariable("screenDateCode") Long screenDateCode, Model model) {
		model.addAttribute("seatDTO", new SeatDTO());
		model.addAttribute("screenDateCode", screenDateCode);
		model.addAttribute("status", SeatStatus.values());
		return "administrator/admin_seat_form";
	}

	@PostMapping("/admin/screenDate/{screenDateCode}/new")
	public String seatForm(Long screenDateCode, SeatDTO seatDTO, BindingResult bindingResult, Model model) {
		if (bindingResult.hasErrors()) {
			return "administrator/admin_seat_form";
		}
		try {
			seatService.saveSeat(seatDTO, screenDateCode);
		} catch (IllegalStateException e) {
			model.addAttribute("errorMessage", e.getMessage());
			return "administrator/admin_seat_form";
		}
		return "redirect:/admin/screenDate/" + screenDateCode;
	}

	@DeleteMapping("/admin/seat/{seatCode}")
	public @ResponseBody ResponseEntity deleteSeat(@PathVariable("seatCode") Long seatCode) {
		seatService.deleteSeat(seatCode);
		return new ResponseEntity(seatCode, HttpStatus.OK);
	}

	@GetMapping("/admin/seat/{seatCode}/update")
	public String updateSeat(@PathVariable("seatCode") Long seatCode, Model model) {
		Seat seat = seatService.getSeat(seatCode);
		SeatDTO seatDTO = SeatDTO.of(seat);
		model.addAttribute(seatDTO);
		return "/administrator/admin_seat_form";
	}

	@PostMapping("/admin/seat/{seatCode}")
	public String updateSeat(Long seatCode, SeatDTO seatDTO) {
		Seat seat = seatService.getSeat(seatCode);
		seatDTO.setCode(seatCode);
		seatService.updateSeat(seatDTO);
		return "redirect:/admin/screenDate/" + seat.getScreenDate().getCode();
	}
}
