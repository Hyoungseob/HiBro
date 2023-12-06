package com.HiBro.entity;

import com.HiBro.repository.TheaterRepository;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@SpringBootTest
@Transactional
@TestPropertySource(locations = "classpath:application-test.properties")
public class TheaterTest {

	@Autowired
	TheaterRepository theaterRepository;

	public void createTheater() {

		for (int i = 1; i <= 10; i++) {
			Theater theater = new Theater();
			theater.setTheaterImg("임시 이미지");
			theater.setTheaterLocation("울산 삼산동" + i);
			theater.setTheaterType("프리미엄" + i);

			theaterRepository.save(theater);
		}
	}

	@Test
	@DisplayName("상영관 검색 테스트")
	public void findByTheaterLocation() {
		this.createTheater();
		List<Theater> theaterList = theaterRepository.findByTheaterLocation("울산 삼산동");
		for (Theater theater : theaterList) {
			System.out.println(theater);
		}
	}

	//삭제 할때 CASCADE : REMOVE 설정 하거나 고아객체로 만들어서 한번에 삭제 가능
	//Seat 엔터티 ManyToOne
	@Test
	@DisplayName("상영관 삭제 테스트")
	public void deleteTheater() {
		this.createTheater();
		Theater theater = theaterRepository.findByTheaterCode(1L);
		theaterRepository.delete(theater);
		List<Theater> theaterList = theaterRepository.findByTheaterLocation("울산 삼산동");
		for (Theater theaters : theaterList) {
			System.out.println(theaters);
		}
	}

}
