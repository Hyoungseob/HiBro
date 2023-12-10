package com.HiBro.controller;

import com.HiBro.constant.ScreenType;
import com.HiBro.constant.TheaterStatus;
import com.HiBro.dto.MemberSearchDTO;
import com.HiBro.dto.ScreenDTO;
import com.HiBro.dto.TheaterDTO;
import com.HiBro.entity.Member;
import com.HiBro.entity.Screen;
import com.HiBro.entity.Theater;
import com.HiBro.repository.ScreenRepository;
import com.HiBro.repository.TheaterRepository;
import com.HiBro.service.MemberService;
import com.HiBro.service.ScreenService;
import com.HiBro.service.TheaterService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.internal.util.Members;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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
	private final TheaterRepository theaterRepository;
	private final ScreenRepository screenRepository;

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
		model.addAttribute("theaterStatus", TheaterStatus.values());
		return "administrator/admin_theater_form";
	}

	@PostMapping("/admin/theater/new")
	public String theaterForm(@RequestParam("theaterStatus") TheaterStatus theaterStatus, TheaterDTO theaterDTO, BindingResult bindingResult, Model model) {
		if (bindingResult.hasErrors()) {
			return "administrator/admin_theater_form";
		}
		try {
			theaterService.saveTheater(theaterDTO);
		} catch (IllegalStateException e) {
			model.addAttribute("errorMessage", e.getMessage());
			return "administrator/admin_theater_form";
		}
		return "administrator/admin_theater";
	}

	@GetMapping("/admin/theater/{theaterCode}")
	public String theaterDtl(@PathVariable("theaterCode") Long theaterCode, Model model) {
		List<Screen> screenList = screenRepository.findScreenByTheaterCode(theaterCode);
		model.addAttribute("screenList", screenList);
		return "administrator/admin_screen";
	}

	@GetMapping("/admin/theater/{theaterCode}/new")
	public String screenForm(@PathVariable("theaterCode") Long theaterCode, Model model) {
		model.addAttribute("screenDTO", new ScreenDTO());
		model.addAttribute("theaterCode", theaterCode);
		model.addAttribute("screenType", ScreenType.values());
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
		//관계테이블 Date -> Seat ?
		return "administrator/admin_screenDate";
	}
}
