package com.HiBro.entity;

import com.HiBro.constant.*;
import com.HiBro.dto.*;
import com.HiBro.repository.ScreenRepository;
import com.HiBro.service.*;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.TestPropertySource;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.*;

@SpringBootTest
@Transactional
@TestPropertySource(locations = "classpath:application-test.properties")
public class ScreenTest {
	@Autowired
	ScreenRepository screenRepository;
	@Autowired
	PasswordEncoder encoder;
	@Autowired
	ScreenService screenService;
	@Autowired
	MemberService memberService;

	public Member createMember() {
		MemberDTO memberDTO = new MemberDTO();
		memberDTO.setId("test");
		memberDTO.setPassword("1234");
		memberDTO.setName("테스트");
		memberDTO.setEmail("a@a");
		memberDTO.setRole(Role.ADMIN);
		memberDTO.setRegDate(LocalDateTime.now());
		return Member.createMember(memberDTO, encoder);
	}

	public List<ScreenDTO> createScreenList() {
		List<ScreenDTO> screenDTOList = new ArrayList<>();

		for (int i = 1; i <= 10; i++) {
			ScreenDTO screenDTO = new ScreenDTO();
			screenDTO.setScreenImg("임시 이미지");
			screenDTO.setScreenLocation("울산 삼산동" + i);
			screenDTO.setScreenType(ScreenType.NORMAL);

			Screen screen = screenService.saveScreen(screenDTO);

			screenDTO.setCode(screen.getCode());

			screenDTOList.add(screenDTO);
		}
		return screenDTOList;
	}

	@Test
	@DisplayName("상영관 검색 테스트")
	public void findByScreenLocation() {
		Member member = createMember();
		memberService.saveMember(member);

		this.createScreenList();

		List<Screen> screenList = screenRepository.findByScreenLocation("울산 삼산동");

		for (Screen screen : screenList) {
			System.out.println(screen);
		}
	}

	@Test
	@DisplayName("상영관 삭제 테스트")
	public void deleteScreen() {
		ScreenDTO screenDTO = this.createScreenList().get(3);
		screenService.deleteScreen(screenDTO);

		List<Screen> screenList = screenRepository.findAll();

		for (Screen screen : screenList) {
			System.out.println(screen);
		}
	}
}
