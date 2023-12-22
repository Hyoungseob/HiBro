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

import java.time.LocalDateTime;
import java.util.*;

@SpringBootTest
@Transactional
@TestPropertySource(locations = "classpath:application-test.properties")
public class SeatTest {
	@Autowired
	SeatRepository seatRepository;
	@Autowired
	ScreenDateRepository screenDateRepository;
	@Autowired
	ScreenService screenService;
	@Autowired
	SeatService seatService;
	@Autowired
	TheaterService theaterService;
	@Autowired
	ScreenDateService screenDateService;

	public TheaterDTO createTheater() {
		TheaterDTO theaterDTO = new TheaterDTO();
//		theaterDTO.setTheaterLocation("울산 남구 삼산동");
		theaterDTO.setTheaterStatus(TheaterStatus.OPEN);

		Theater theater = theaterService.saveTheater(theaterDTO);

		theaterDTO.setCode(theater.getCode());

		return theaterDTO;
	}

	public Screen createScreen() {
		TheaterDTO theaterDTO = this.createTheater();
		ScreenDTO screenDTO = new ScreenDTO();
//		screenDTO.setScreenImg("임시 이미지");
		screenDTO.setScreenLocation("울산 삼산동");
		screenDTO.setScreenType(ScreenType.NORMAL);
		return screenService.saveScreen(screenDTO, theaterDTO.getCode());
	}

	public ScreenDateDTO createScreenDate() {
		Screen screen = createScreen();
		ScreenDateDTO screenDateDTO = new ScreenDateDTO();
		screenDateDTO.setScreeningDateTime(LocalDateTime.now());
		screenDateDTO.setScreeningTime(ScreeningTime.MATINEE);
		ScreenDate screenDate = screenDateService.saveScreenDate(screenDateDTO, screen.getCode());
		screenDateDTO.setCode(screenDate.getCode());

		return screenDateDTO;
	}

	public List<SeatDTO> createSeatList() {
		ScreenDateDTO screenDateDTO = this.createScreenDate();

		List<SeatDTO> seatList = new ArrayList<>();

		for (int i = 1; i <= 10; i++) {
			SeatDTO seatDTO = new SeatDTO();
			seatDTO.setSeatRow("1행");
			seatDTO.setSeatColumn(i + "열");
			seatDTO.setSeatStatus(SeatStatus.SELL);
			Seat seat = seatService.saveSeat(seatDTO, screenDateDTO.getCode());
			seatDTO.setCode(seat.getCode());
			seatList.add(seatDTO);
		}
		return seatList;
	}

	@Test
	@DisplayName("좌석 검색 테스트")
	public void findByScreenLocation() {
		this.createSeatList();

		Long screenCode = screenDateRepository.findAll().get(0).getCode();
		List<Seat> seatList = seatRepository.findByScreenDateCode(screenCode);

		for (Seat seat : seatList) {
			System.out.println(seat);
		}
	}

	@Test
	@DisplayName("좌석 삭제 테스트")
	public void deleteScreenDate() {
		SeatDTO seatDTO = this.createSeatList().get(4);

		Long screenDateCode = screenDateRepository.findAll().get(0).getCode();

		seatService.deleteSeat(seatDTO.getCode());
		List<Seat> seats = seatRepository.findByScreenDateCode(screenDateCode);

		for (Seat seat : seats) {
			System.out.println(seat);
		}
	}


}
