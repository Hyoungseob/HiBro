package com.HiBro.entity;

import com.HiBro.repository.TheaterRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
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

}
