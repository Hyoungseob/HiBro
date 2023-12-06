package com.HiBro.entity;

import com.HiBro.constant.SeatStatus;
import com.HiBro.repository.*;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@SpringBootTest
@Transactional
@TestPropertySource(locations = "classpath:application-test.properties")
public class SeatTest {

	@Autowired
	SeatRepository seatRepository;

	@Autowired
	TheaterRepository theaterRepository;

	public void createSeat() {

		Theater theater = new Theater();
		theater.setTheaterImg("임시 이미지");
		theater.setTheaterLocation("울산 삼산동");
		theater.setTheaterType("프리미엄");
		theaterRepository.save(theater);

		for (int i = 1; i <= 10; i++) {
			Seat seat = new Seat();
			seat.setSeatRow("1행");
			seat.setSeatColumn(i + "열");
			seat.setSeatStatus(SeatStatus.SELL);
			seat.setTheater(theater);
			seatRepository.save(seat);
		}
	}

	@Test
	@DisplayName("좌석 검색 테스트")
	public void findByTheaterLocation() {
		this.createSeat();
		Long theaterCode = theaterRepository.findAll().get(0).getTheaterCode();
		List<Seat> seatList = seatRepository.findSeatByTheaterCode(theaterCode);
		for (Seat seat : seatList) {
			System.out.println(seat);
		}
	}


}
