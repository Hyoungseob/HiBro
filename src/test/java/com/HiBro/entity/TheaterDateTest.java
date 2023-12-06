package com.HiBro.entity;

import com.HiBro.constant.ScreeningTime;
import com.HiBro.repository.*;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@SpringBootTest
@Transactional
@TestPropertySource(locations = "classpath:application-test.properties")
public class TheaterDateTest {

	@Autowired
	TheaterDateRepository theaterDateRepository;
	@Autowired
	TheaterRepository theaterRepository;

	public void createTheaterDate() {
		Theater theater = new Theater();
		theater.setTheaterImg("임시 이미지");
		theater.setTheaterLocation("울산 삼산동");
		theater.setTheaterType("프리미엄");
		theaterRepository.save(theater);

		for (int i = 1; i <= 10; i++) {
			TheaterDate theaterDate = new TheaterDate();
			theaterDate.setScreeningDateTime(LocalDateTime.now());
			theaterDate.setScreeningTime(ScreeningTime.MATINEE);
			theaterDate.setTheater(theater);
			theaterDateRepository.save(theaterDate);
		}
	}

	@Test
	@DisplayName("상영관 스케쥴 검색 테스트")
	public void findByTheaterLocation() {
		this.createTheaterDate();

		Long theaterCode = theaterRepository.findAll().get(0).getTheaterCode();
		List<TheaterDate> theaterDates = theaterDateRepository.findTheaterDateByTheaterCode(theaterCode);
		for (TheaterDate theaterDate : theaterDates) {
			System.out.println(theaterDate);
		}
	}


}
