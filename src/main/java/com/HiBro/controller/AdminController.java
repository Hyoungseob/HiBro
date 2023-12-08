package com.HiBro.controller;

import com.HiBro.dto.MemberSearchDTO;
import com.HiBro.entity.Member;
import com.HiBro.service.MemberService;
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
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class AdminController{
    private final MemberService memberService;

    @GetMapping("/admin")
    public String admin(MemberSearchDTO memberSearchDTO, Model model, Optional<Integer> page){
        Pageable pageable = PageRequest.of(page.isPresent()? page.get():0,10);
        Page<Member> memberList = memberService.getMemberAll(memberSearchDTO,pageable) ;
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

    @DeleteMapping("/admin/member/{member_code}")
    public @ResponseBody ResponseEntity deleteMember(@PathVariable("member_code") Long memberCode, Principal principal){
        memberService.deleteMember(memberCode);
        return new ResponseEntity<Long>(memberCode, HttpStatus.OK);
    }
}
