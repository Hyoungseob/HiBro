package com.HiBro.entity;

import com.HiBro.constant.*;
import com.HiBro.dto.*;
import com.HiBro.repository.TheaterRepository;
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
public class TheaterTest {
	@Autowired
	TheaterService theaterService;
	@Autowired
	TheaterRepository theaterRepository;
	@Autowired
	ScreenService screenService;
	@Autowired
	SeatService seatService;
	@Autowired
	ScreenDateService screenDateService;

	public TheaterDTO createTheater() {
		TheaterDTO theaterDTO = new TheaterDTO();
		theaterDTO.setTheaterName("바보");
		theaterDTO.setLocation(Location.DAEGU);
		theaterDTO.setTheaterStatus(TheaterStatus.OPEN);
		Theater theater = theaterService.saveTheater(theaterDTO);
		theaterDTO.setCode(theater.getCode());
		return theaterDTO;
	}

	public List<TheaterDTO> createTheaterList() {
		List<TheaterDTO> theaterDTOList = new ArrayList<>();

		for (int i = 0; i < 10; i++) {
			TheaterDTO theaterDTO = new TheaterDTO();
//			theaterDTO.setTheaterLocation("울산 남구 삼산동");
			theaterDTO.setTheaterStatus(TheaterStatus.OPEN);

			Theater theater = theaterService.saveTheater(theaterDTO);

			theaterDTO.setCode(theater.getCode());

			theaterDTOList.add(theaterDTO);
		}
		return theaterDTOList;
	}

	public List<ScreenDTO> createScreenList(TheaterDTO theaterDTO) {
		List<ScreenDTO> screenDTOList = new ArrayList<>();
		for (int i = 0; i < 10; i++) {
			ScreenDTO screenDTO = new ScreenDTO();
//			screenDTO.setScreenImg("임시 이미지");
			screenDTO.setScreenLocation("울산 삼산동");
			screenDTO.setScreenType(ScreenType.NORMAL);

			Screen screen = screenService.saveScreen(screenDTO, theaterDTO.getCode());
			screenDTO.setCode(screen.getCode());
			screenDTOList.add(screenDTO);
		}
		return screenDTOList;
	}

	public List<SeatDTO> createSeatList(ScreenDTO screenDTO) {

		List<SeatDTO> seatList = new ArrayList<>();

		for (int i = 1; i <= 10; i++) {
			SeatDTO seatDTO = new SeatDTO();
			seatDTO.setSeatRow("1행");
			seatDTO.setSeatColumn(i + "열");
			seatDTO.setSeatStatus(SeatStatus.SELL);
			Seat seat = seatService.saveSeat(seatDTO, screenDTO.getCode());
			seatDTO.setCode(seat.getCode());
			seatList.add(seatDTO);
		}
		return seatList;
	}

	public List<ScreenDateDTO> createScreenDateList(ScreenDTO screenDTO) {

		List<ScreenDateDTO> screenDateDTOList = new ArrayList<>();

		for (int i = 1; i <= 10; i++) {
			ScreenDateDTO screenDateDTO = new ScreenDateDTO();
			screenDateDTO.setScreeningDateTime(LocalDateTime.now());
			screenDateDTO.setScreeningTime(ScreeningTime.MATINEE);

			ScreenDate screenDate = screenDateService.saveScreenDate(screenDateDTO, screenDTO.getCode());
			screenDateDTO.setCode(screenDate.getCode());

			screenDateDTOList.add(screenDateDTO);
		}
		return screenDateDTOList;
	}


	@Test
	@DisplayName("영화관 검색")
	public void findTheater() {
		List<TheaterDTO> theaterDTOList = this.createTheaterList();
		Theater theater = theaterService.saveTheater(theaterDTOList.get(3));
		System.out.println(theater);
	}

	@Test
	@DisplayName("영화관 삭제")
	public void deleteTheater() {
		TheaterDTO theaterDTO = this.createTheaterList().get(3);
		List<ScreenDTO> screenDTOList = this.createScreenList(theaterDTO);
		ScreenDTO screenDTO = screenDTOList.get(3);
		this.createSeatList(screenDTO);
		this.createScreenDateList(screenDTO);
		theaterService.deleteTheater(theaterDTO.getCode());

		List<Theater> theaterList = theaterRepository.findAll();
		for (Theater theater : theaterList) {
			System.out.println(theater);
		}
	}

	@Test
	@DisplayName("영화관 수정")
	public void updateTheater() {
		TheaterDTO theaterDTO = this.createTheater();
		Theater theater = theaterRepository.findByCode(theaterDTO.getCode());
		System.out.println(theater);
		theaterDTO.setTheaterName("천재");
		theaterDTO.setLocation(Location.BUSAN);
		theaterDTO.setTheaterStatus(TheaterStatus.CLOSE);
		//theater.updateTheater(theaterDTO);
		System.out.println(theater);
	}
}
