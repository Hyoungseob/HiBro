package com.HiBro.entity;

import com.HiBro.constant.Role;
import com.HiBro.dto.MemberDTO;
import com.HiBro.repository.MemberRepository;
import com.HiBro.service.MemberService;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.TestPropertySource;

import javax.transaction.Transactional;
import java.time.LocalDateTime;

@SpringBootTest
@Transactional
@TestPropertySource(locations = "classpath:application-test.properties")
public class MemberTest {
	@Autowired
	MemberRepository memberRepository;
	@Autowired
	MemberService memberService;
	@Autowired
	PasswordEncoder encoder;

	@Test
	@DisplayName("멤버 생성 테스트")
	public void createMember() {
		MemberDTO memberDTO = new MemberDTO();

		memberDTO.setId("test");
		memberDTO.setPassword("1234");
		memberDTO.setName("테스트");
		memberDTO.setEmail("a@a");
		memberDTO.setRole(Role.USER);
		memberDTO.setRegDate(LocalDateTime.now());

		Member member = Member.createMember(memberDTO, encoder);
		System.out.println(".. ");
		memberService.saveMember(member);
		Member savedMember = memberRepository.findById("test");
		System.out.println(savedMember.toString());
	}
}
