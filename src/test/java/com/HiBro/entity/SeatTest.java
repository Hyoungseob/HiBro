package com.HiBro.entity;

import com.HiBro.constant.SeatStatus;
import com.HiBro.repository.SeatRepository;
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
public class SeatTest {

	@Autowired
	SeatRepository seatRepository;
	public void createSeat() {

		for (int i = 1; i <= 10; i++) {
			Seat seat = new Seat();
			seat.setSeatRow("1행");
			seat.setSeatColumn("" + i + "열");
			seat.setSeatStatus(SeatStatus.SELL);
		}
	}

	@Test
	@DisplayName("좌석 검색 테스트")
	public void findByTheaterLocation() {
		this.createSeat();
		List<Seat> seatList = seatRepository.findByTheaterCode(1L);
		for (Seat seat : seatList) {
			System.out.println(seat);
		}
	}


}
