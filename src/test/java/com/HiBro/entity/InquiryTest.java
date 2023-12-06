package com.HiBro.entity;

import com.HiBro.constant.InquiryStatus;
import com.HiBro.constant.Role;
import com.HiBro.dto.InquiryDTO;
import com.HiBro.dto.MemberDTO;
import com.HiBro.repository.InquiryRepository;
import com.HiBro.service.InquiryService;
import com.HiBro.service.MemberService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.TestPropertySource;

import javax.transaction.Transactional;
import java.time.LocalDateTime;

@SpringBootTest
@Transactional
@TestPropertySource(locations = "classpath:application-test.properties")
public class InquiryTest{
    @Autowired
    InquiryRepository inquiryRepository;
    @Autowired
    InquiryService inquiryService;
    @Autowired
    MemberService memberService;


    @Autowired
    PasswordEncoder encoder;
    public Member createMember(){
        MemberDTO memberDTO = new MemberDTO();

        memberDTO.setId("test");
        memberDTO.setPassword("1234");
        memberDTO.setName("테스트");
        memberDTO.setEmail("a@a");
        memberDTO.setRole(Role.USER);
        memberDTO.setRegDate(LocalDateTime.now());
        return Member.createMember(memberDTO,encoder);
    }
    public InquiryDTO createInquiryDTO(){
        InquiryDTO inquiryDTO = new InquiryDTO();
        inquiryDTO.setTitle("문의 제목 테스트");
        inquiryDTO.setContent("문의 내용 테스트");
        inquiryDTO.setInquiryStatus(InquiryStatus.ING);
        inquiryDTO.setRegDate(LocalDateTime.now());
        return inquiryDTO;
    }
    @Test
    @DisplayName("문의사항 생성 삭제 테스트")
    public void inquiryTest(){
        Member member = createMember();
        memberService.saveMember(member);
        InquiryDTO inquiryDTO = createInquiryDTO();
        System.out.println(member.getId());
        Inquiry inquiry= inquiryService.saveInquiry(inquiryDTO,member.getId());
        inquiryDTO.setCode(inquiry.getCode());
        //inquiryRepository.delete(inquiry);
        inquiryService.deleteInquiry(inquiryDTO);

    }
}
