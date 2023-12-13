package com.HiBro.controller;

import com.HiBro.dto.MemberDTO;
import com.HiBro.entity.Member;
import com.HiBro.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequiredArgsConstructor
@RequestMapping("/member")
public class MemberController {
	private final MemberService	memberService;
	private final PasswordEncoder passwordEncoder;
	@GetMapping("/new")
	public String theater(Model model) {
		model.addAttribute("memberDTO", new MemberDTO());
		return "member_form";
	}

	@PostMapping(value = "/new")
	public String memberForm(@Valid MemberDTO memberDTO, BindingResult bindingResult, Model model) {

		//오류가 있으면 회원가입페이지로 이동
		if (bindingResult.hasErrors()) {
			return "member_form";
		}

		try {
			Member member = Member.createMember(memberDTO, passwordEncoder);
			memberService.saveMember(member);
		} catch (IllegalStateException e) {
			model.addAttribute("errorMessage", e.getMessage());
			return "member_form";
		}

		return "redirect:/";
	}
}
