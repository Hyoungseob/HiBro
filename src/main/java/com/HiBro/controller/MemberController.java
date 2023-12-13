package com.HiBro.controller;

import com.HiBro.dto.MemberDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/member")
public class MemberController {
	@GetMapping("/new")
	public String theater(Model model) {
		model.addAttribute("memberDTO", new MemberDTO());
		return "member_form";
	}
}
