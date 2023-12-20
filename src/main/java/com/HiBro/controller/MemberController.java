package com.HiBro.controller;

import com.HiBro.dto.MemberDTO;
import com.HiBro.entity.Member;
import com.HiBro.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;

@Controller
@RequiredArgsConstructor
@RequestMapping("/member")
public class MemberController {
	private final MemberService memberService;
	private final PasswordEncoder passwordEncoder;

	@GetMapping("/new")
	public String theater(Model model) {
		model.addAttribute("memberDTO", new MemberDTO());
		return "member_form";
	}

	@PostMapping(value = "/new")
	public String memberForm(@Valid MemberDTO memberDTO, BindingResult bindingResult, Model model) {
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

	@GetMapping("/login")
	public String loginMember(Principal principal) {
		if (principal != null) {
			return "redirect:/";
		}
		return "/login_form";
	}

	@GetMapping("/login/error")
	public String loginError(Model model) {
		model.addAttribute("loginErrorMsg", "아이디 또는 비밀번호를 확인하세요");
		return "/Login_form";
	}

}
