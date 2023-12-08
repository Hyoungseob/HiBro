package com.HiBro.entity;

import com.HiBro.constant.SeatStatus;
import com.HiBro.dto.*;
import com.HiBro.repository.*;
import com.HiBro.service.*;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
@Transactional
@TestPropertySource(locations = "classpath:application-test.properties")
public class SeatTest {

	@Autowired
	SeatRepository seatRepository;

	@Autowired
	TheaterRepository theaterRepository;
	@Autowired
	TheaterService theaterService;
	@Autowired
	SeatService seatService;


	public Theater createTheater() {
		TheaterDTO theaterDTO = new TheaterDTO();
		theaterDTO.setTheaterImg("임시 이미지");
		theaterDTO.setTheaterLocation("울산 삼산동");
		theaterDTO.setTheaterType("프리미엄");

		Theater theater = theaterService.saveTheater(theaterDTO);

		return theater;
	}

	public List<SeatDTO> createSeatList() {

		Theater theater = createTheater();
		List<SeatDTO> seatList = new ArrayList<>();

		for (int i = 1; i <= 10; i++) {
			SeatDTO seatDTO = new SeatDTO();
			seatDTO.setSeatRow("1행");
			seatDTO.setSeatColumn(i + "열");
			seatDTO.setSeatStatus(SeatStatus.SELL);

			Seat seat = seatService.saveSeat(seatDTO, theater.getCode());

			seatDTO.setCode(seat.getCode());

			seatList.add(seatDTO);
		}
		return seatList;
	}

	@Test
	@DisplayName("좌석 검색 테스트")
	public void findByTheaterLocation() {
		this.createSeatList();
		Long theaterCode = theaterRepository.findAll().get(0).getCode();
		List<Seat> seatList = seatRepository.findSeatByTheaterCode(theaterCode);
		for (Seat seat : seatList) {
			System.out.println(seat);
		}
	}

	@Test
	@DisplayName("좌석 삭제 테스트")
	public void deleteTheaterDate() {
		SeatDTO seatDTO = this.createSeatList().get(4);

		Long theaterCode = theaterRepository.findAll().get(0).getCode();

		seatService.deleteSeat(seatDTO.getCode());
		List<Seat> seats = seatRepository.findSeatByTheaterCode(theaterCode);
		for (Seat seat : seats) {
			System.out.println(seat);
		}
	}


}
