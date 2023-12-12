package com.HiBro.controller;

import com.HiBro.constant.*;
import com.HiBro.dto.*;
import com.HiBro.entity.*;
import com.HiBro.repository.ScreenDateRepository;
import com.HiBro.repository.ScreenRepository;
import com.HiBro.repository.SeatRepository;
import com.HiBro.repository.TheaterRepository;
import com.HiBro.service.*;
import lombok.RequiredArgsConstructor;
import org.modelmapper.internal.util.Members;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class AdminController {
	private final MemberService memberService;
	private final TheaterService theaterService;
	private final ScreenService screenService;
	private final ScreenDateService screenDateService;
	private final SeatService seatService;
	private final TheaterRepository theaterRepository;
	private final ScreenRepository screenRepository;
	private final ScreenDateRepository screenDateRepository;
	private final SeatRepository seatRepository;

	@GetMapping("/admin")
	public String admin(MemberSearchDTO memberSearchDTO, Model model, Optional<Integer> page) {
		Pageable pageable = PageRequest.of(page.isPresent() ? page.get() : 0, 10);
		Page<Member> memberList = memberService.getMemberAll(memberSearchDTO, pageable);
		model.addAttribute("memberList", memberList);
		model.addAttribute("maxPage", 5);
		return "administrator/admin_member";
	}

	@GetMapping("/admin/point")
	public String point() {
		return "administrator/admin_point";
	}

	@GetMapping("/admin/inquiry")
	public String inquiry() {
		return "administrator/admin_inquiry";
	}

	@DeleteMapping("/admin/member/{member_code}")
	public @ResponseBody ResponseEntity deleteMember(@PathVariable("member_code") Long memberCode, Principal principal) {
		memberService.deleteMember(memberCode);
		return new ResponseEntity<Long>(memberCode, HttpStatus.OK);
	}

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
		model.addAttribute("screenDateList", screenDateList);
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
