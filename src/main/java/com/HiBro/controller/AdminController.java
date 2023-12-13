package com.HiBro.controller;

import com.HiBro.constant.*;
import com.HiBro.dto.*;
import com.HiBro.entity.*;
import com.HiBro.repository.*;
import com.HiBro.service.*;
import lombok.RequiredArgsConstructor;
import org.modelmapper.internal.util.Members;
import org.springframework.data.domain.*;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class AdminController {
	private final TheaterService theaterService;
	private final ScreenService screenService;
	private final ScreenDateService screenDateService;
	private final SeatService seatService;
	private final TheaterRepository theaterRepository;
	private final ScreenRepository screenRepository;
	private final ScreenDateRepository screenDateRepository;
	private final SeatRepository seatRepository;

	@GetMapping("/admin/theater")
	public String theater(Model model) {
		//repository 말고 service로 처리?
		List<Theater> theaterList = theaterRepository.findAll();
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

	@GetMapping("/admin/theater/{theaterCode}")
	public String theaterDtl(@PathVariable("theaterCode") Long theaterCode, Model model) {
		List<Screen> screenList = screenRepository.findScreenByTheaterCode(theaterCode);
		Theater theater = theaterRepository.findByCode(theaterCode);
		model.addAttribute(theater);
		model.addAttribute("screenList", screenList);
		return "administrator/admin_screen";
	}
	@DeleteMapping("/admin/theater/{theaterCode}")
	public @ResponseBody ResponseEntity deleteTheater(@PathVariable("theaterCode") Long theaterCode, Model model) {
		theaterService.deleteTheater(theaterCode);
		return new ResponseEntity(theaterCode,HttpStatus.OK);
	}

	@GetMapping("/admin/theater/{theaterCode}/new")
	public String screenForm(@PathVariable("theaterCode") Long theaterCode, Model model) {
		model.addAttribute("screenDTO", new ScreenDTO());
		model.addAttribute("theaterCode", theaterCode);
		return "administrator/admin_screen_form";
	}

	@PostMapping("/admin/theater/{theaterCode}/new")
	public String screenForm(@RequestParam("theaterCode") String theaterCode, @RequestParam("screenType") ScreenType screenType, ScreenDTO screenDTO, BindingResult bindingResult, Model model) {
		if (bindingResult.hasErrors()) {
			return "administrator/admin_screen_form";
		}
		try {
			screenService.saveScreen(screenDTO, Long.valueOf(theaterCode));
		} catch (IllegalStateException e) {
			model.addAttribute("errorMessage", e.getMessage());
			return "administrator/admin_screen_form";
		}
		return "administrator/admin_screen";
	}

	@GetMapping("/admin/screen/{screenCode}")
	public String screenDtl(@PathVariable("screenCode") Long screenCode, Model model) {
		List<ScreenDate> screenDateList = screenDateRepository.findScreenDateByScreenCode(screenCode);
		Screen screen = screenRepository.findByCode(screenCode);
		Theater theater = screen.getTheater();
		model.addAttribute("screenDateList", screenDateList);
		model.addAttribute(screen);
		model.addAttribute(theater);
		return "administrator/admin_screenDate";
	}

	@GetMapping("/admin/screen/{screenCode}/new")
	public String screenDateForm(@PathVariable("screenCode") Long screenCode, Model model) {
		model.addAttribute("screenDateDTO", new ScreenDateDTO());
		model.addAttribute("screenCode", screenCode);
		model.addAttribute("screeningTime", ScreeningTime.values());
		return "administrator/admin_screenDate_form";
	}

	@PostMapping("/admin/screen/{screenCode}/new")
	public String screenDateForm(@RequestParam("screenCode") String screenCode, @RequestParam("screeningTime") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) ScreeningTime screeningTime, ScreenDateDTO screenDateDTO, BindingResult bindingResult, Model model) {
		if (bindingResult.hasErrors()) {
			return "administrator/admin_screenDate_form";
		}
		try {
			screenDateService.saveScreenDate(screenDateDTO, Long.valueOf(screenCode));
		} catch (IllegalStateException e) {
			model.addAttribute("errorMessage", e.getMessage());
			return "administrator/admin_screenDate_form";
		}
		return "administrator/admin_screenDate";
	}

	@GetMapping("/admin/screenDate/{screenDateCode}")
	public String screenDateDtl(@PathVariable("screenDateCode") Long screenDateCode, Model model) {
		List<Seat> seatList = seatRepository.findSeatByScreenDateCode(screenDateCode);
		ScreenDate screenDate = screenDateRepository.findByCode(screenDateCode);
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
		model.addAttribute("seatStatus", SeatStatus.values());
		return "administrator/admin_seat_form";
	}

	@PostMapping("/admin/screenDate/{screenDateCode}/new")
	public String seatForm(@RequestParam("screenDateCode") String screenDateCode, @RequestParam("seatStatus") SeatStatus seatStatus, SeatDTO seatDTO, BindingResult bindingResult, Model model) {
		if (bindingResult.hasErrors()) {
			return "administrator/admin_seat_form";
		}
		try {
			seatService.saveSeat(seatDTO, Long.valueOf(screenDateCode));
		} catch (IllegalStateException e) {
			model.addAttribute("errorMessage", e.getMessage());
			return "administrator/admin_seat_form";
		}
		return "administrator/admin_seat";
	}
}
