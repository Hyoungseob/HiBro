package com.HiBro.entity;

import com.HiBro.constant.Role;
import com.HiBro.dto.*;
import com.HiBro.repository.TheaterRepository;
import com.HiBro.service.*;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.TestPropertySource;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest
@Transactional
@TestPropertySource(locations = "classpath:application-test.properties")
public class TheaterTest {

	@Autowired
	TheaterRepository theaterRepository;
	@Autowired
	PasswordEncoder encoder;
	@Autowired
	TheaterService theaterService;
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

	public List<TheaterDTO> createTheaterList() {

		List<TheaterDTO> theaterDTOList = new ArrayList<>();

		for (int i = 1; i <= 10; i++) {
			TheaterDTO theaterDTO = new TheaterDTO();
			theaterDTO.setTheaterImg("임시 이미지");
			theaterDTO.setTheaterLocation("울산 삼산동" + i);
			theaterDTO.setTheaterType("프리미엄" + i);

			Theater theater = theaterService.saveTheater(theaterDTO);

			theaterDTO.setCode(theater.getCode());

			theaterDTOList.add(theaterDTO);
		}
		return theaterDTOList;
	}

	@Test
	@DisplayName("상영관 검색 테스트")
	public void findByTheaterLocation() {
		Member member = createMember();
		memberService.saveMember(member);

		this.createTheaterList();
		List<Theater> theaterList = theaterRepository.findByTheaterLocation("울산 삼산동");
		for (Theater theater : theaterList) {
			System.out.println(theater);
		}
	}

	@Test
	@DisplayName("상영관 삭제 테스트")
	public void deleteTheater() {
		TheaterDTO theaterDTO = this.createTheaterList().get(3);
		theaterService.deleteTheater(theaterDTO);
		List<Theater> theaterList = theaterRepository.findAll();
		for (Theater theater : theaterList) {
			System.out.println(theater);
		}
	}

}
