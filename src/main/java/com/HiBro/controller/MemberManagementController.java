package com.HiBro.controller;

import com.HiBro.dto.AnswerDTO;
import com.HiBro.dto.MemberSearchDTO;
import com.HiBro.entity.Answer;
import com.HiBro.entity.Inquiry;
import com.HiBro.entity.Member;
import com.HiBro.service.AnswerService;
import com.HiBro.service.InquiryService;
import com.HiBro.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class MemberManagementController{
    private final MemberService memberService;
    private final InquiryService inquiryService;
    private final AnswerService answerService;

    @GetMapping("/admin")
    public String getMemberList(MemberSearchDTO memberSearchDTO, Model model, Optional<Integer> page) {
        Pageable pageable = PageRequest.of(page.isPresent() ? page.get() : 0, 10);
        Page<Member> memberList = memberService.getMemberAll(memberSearchDTO, pageable);

        model.addAttribute("memberList", memberList);
        model.addAttribute("maxPage", 5);
        return "administrator/admin_member";
    }
    @DeleteMapping("/admin/member/delete/{member_code}")
    public @ResponseBody ResponseEntity deleteMember(@PathVariable("member_code") Long memberCode, Principal principal) {
        inquiryService.deleteMemberInquiry(memberCode);
        memberService.deleteMember(memberCode);
        return new ResponseEntity<Long>(memberCode, HttpStatus.OK);
    }
    @PatchMapping("/admin/member/update/{member_code}")
    public @ResponseBody ResponseEntity updateMember(@PathVariable("member_code")Long memberCode,int point,Principal principal){
        memberService.updateMember(memberCode,point);
        return new ResponseEntity<Long>(memberCode,HttpStatus.OK);
    }
    @GetMapping("/admin/inquiry")
    public String getInquiryList(Model model, Optional<Integer> page) {
        Pageable pageable = PageRequest.of(page.isPresent() ? page.get() : 0, 10);
        Page<Inquiry> inquiryList = inquiryService.getInquiryAll(pageable);

        model.addAttribute("inquiryList", inquiryList);
        model.addAttribute("maxPage", 5);
        return "administrator/admin_inquiryList";
    }
    @GetMapping("/admin/inquiry/{inquiry_code}")
    public String getInquiry(@PathVariable("inquiry_code") Long inquirycode, Model model){
        Inquiry inquiry=inquiryService.getInqury(inquirycode);
        model.addAttribute("inquiry",inquiry);
        Answer answer = answerService.getAnswer(inquirycode);
        AnswerDTO answerDTO= Answer.of(answer);
        model.addAttribute("answerDTO",answerDTO);
        return "administrator/admin_inquiry";
    }
    @PostMapping("/admin/inquiry_answer/new")
    public String saveAnswer(AnswerDTO answerDTO,Principal principal){
        return "redirect:/admin/inquiry";
    }
}
