package com.HiBro.controller;


import com.HiBro.constant.Role;
import com.HiBro.dto.MemberDTO;
import com.HiBro.entity.Member;
import com.HiBro.service.MemberService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestBuilders.formLogin;

@SpringBootTest
@Transactional
@TestPropertySource("classpath:application-test.properties")
@AutoConfigureMockMvc
public class MemberControllerTest {

	@Autowired
	MemberService memberService;
	@Autowired
	private MockMvc mockMvc;
	@Autowired
	PasswordEncoder passwordEncoder;

	//테스트에서 사용할 회원을 생성
	public Member createMember(String id, String password) {
		MemberDTO memberDTO = new MemberDTO();

		memberDTO.setId(id);
		memberDTO.setEmail("test@email.com");
		memberDTO.setName("테스트");
		memberDTO.setEmail("a@a");
		memberDTO.setRole(Role.USER);
		memberDTO.setRegDate(LocalDateTime.now().toLocalDate());
		memberDTO.setPassword(password);
		Member member = Member.createMember(memberDTO, passwordEncoder);
		System.out.println(member + "멤버");
		return memberService.saveMember(member);
	}

	@Test
	@DisplayName("로그인 성공 테스트")
	public void loginSuccessTest() throws Exception {
		String id = "test";
		String password = "1234";
		this.createMember(id, password);

		mockMvc.perform(formLogin().userParameter("id")
						.loginProcessingUrl("/member/login")
						.user(id)
						.password(password))
				.andExpect(SecurityMockMvcResultMatchers.authenticated());

	}
}
