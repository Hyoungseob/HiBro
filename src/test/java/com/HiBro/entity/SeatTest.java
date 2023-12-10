package com.HiBro.entity;

import com.HiBro.constant.*;
import com.HiBro.dto.*;
import com.HiBro.repository.*;
import com.HiBro.service.*;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@SpringBootTest
@Transactional
@TestPropertySource(locations = "classpath:application-test.properties")
public class SeatTest {
	@Autowired
	SeatRepository seatRepository;
	@Autowired
	ScreenRepository screenRepository;
	@Autowired
	ScreenService screenService;
	@Autowired
	SeatService seatService;
	@Autowired
	TheaterService theaterService;

	public TheaterDTO createTheater() {
		TheaterDTO theaterDTO = new TheaterDTO();
		theaterDTO.setTheaterLocation("울산 남구 삼산동");
		theaterDTO.setTheaterStatus(TheaterStatus.OPEN);

		Theater theater = theaterService.saveTheater(theaterDTO);

		theaterDTO.setCode(theater.getCode());

		return theaterDTO;
	}

	public Screen createScreen() {
		TheaterDTO theaterDTO = this.createTheater();
		ScreenDTO screenDTO = new ScreenDTO();
		screenDTO.setScreenImg("임시 이미지");
		screenDTO.setScreenLocation("울산 삼산동");
		screenDTO.setScreenType(ScreenType.NORMAL);
		return screenService.saveScreen(screenDTO, theaterDTO.getCode());
	}

	public List<SeatDTO> createSeatList() {
		Screen screen = createScreen();

		List<SeatDTO> seatList = new ArrayList<>();

		for (int i = 1; i <= 10; i++) {
			SeatDTO seatDTO = new SeatDTO();
			seatDTO.setSeatRow("1행");
			seatDTO.setSeatColumn(i + "열");
			seatDTO.setSeatStatus(SeatStatus.SELL);
			Seat seat = seatService.saveSeat(seatDTO, screen.getCode());
			seatDTO.setCode(seat.getCode());
			seatList.add(seatDTO);
		}
		return seatList;
	}

	@Test
	@DisplayName("좌석 검색 테스트")
	public void findByScreenLocation() {
		this.createSeatList();

		Long screenCode = screenRepository.findAll().get(0).getCode();
		List<Seat> seatList = seatRepository.findSeatByScreenCode(screenCode);

		for (Seat seat : seatList) {
			System.out.println(seat);
		}
	}

	@Test
	@DisplayName("좌석 삭제 테스트")
	public void deleteScreenDate() {
		SeatDTO seatDTO = this.createSeatList().get(4);

		Long screenCode = screenRepository.findAll().get(0).getCode();

		seatService.deleteSeat(seatDTO);
		List<Seat> seats = seatRepository.findSeatByScreenCode(screenCode);

		for (Seat seat : seats) {
			System.out.println(seat);
		}
	}


}
