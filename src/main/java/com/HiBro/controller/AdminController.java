package com.HiBro.controller;

import com.HiBro.entity.Member;
import com.HiBro.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.internal.util.Members;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class AdminController{
    MemberService memberService;

    @GetMapping("/admin")
    public String admin(Model model, Optional<Integer> page){
        Pageable pageable = PageRequest.of(page.isPresent()? page.get():0,7);
        List<Member> memberList = memberService.getMemberAll() ;
        model.addAttribute("memberList",memberList);
        model.addAttribute("maxPage",5);
        return "administrator/admin_member";
    }

    @GetMapping("/admin/point")
    public String point(){
        return "administrator/admin_point";
    }

    @GetMapping("/admin/inquiry")
    public String inquiry(){
        return "administrator/admin_inquiry";
    }
}
