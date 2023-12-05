package com.HiBro.entity;

import com.HiBro.constant.ScreeningTime;
import com.HiBro.repository.TheaterDateRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
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
	public void createTheaterDate() {
		for (int i = 1; i <= 10; i++) {
			TheaterDate theaterDate = new TheaterDate();
			theaterDate.setScreeningDateTime(LocalDateTime.now());
			theaterDate.setScreeningTime(ScreeningTime.MATINEE);

			theaterDateRepository.save(theaterDate);
		}
	}

	@Test
	@DisplayName("상영관 스케쥴 검색 테스트")
	public void findByTheaterLocation() {
		this.createTheaterDate();
//		TheaterDate theaterDate = theaterDateRepository.findByTheaterCode(1L);
	}


}
