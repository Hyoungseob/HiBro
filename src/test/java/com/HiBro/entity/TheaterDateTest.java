package com.HiBro.entity;

import com.HiBro.constant.ScreeningTime;
import com.HiBro.dto.*;
import com.HiBro.repository.*;
import com.HiBro.service.*;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest
@Transactional
@TestPropertySource(locations = "classpath:application-test.properties")
public class TheaterDateTest {

	@Autowired
	TheaterDateRepository theaterDateRepository;
	@Autowired
	TheaterRepository theaterRepository;
	@Autowired
	TheaterService theaterService;
	@Autowired
	TheaterDateService theaterDateService;

	public Theater createTheater() {
		TheaterDTO theaterDTO = new TheaterDTO();
		theaterDTO.setTheaterImg("임시 이미지");
		theaterDTO.setTheaterLocation("울산 삼산동");
		theaterDTO.setTheaterType("프리미엄");

		Theater theater = theaterService.saveTheater(theaterDTO);

		return theater;
	}

	public List<TheaterDateDTO> createTheaterDateList() {

		Theater theater = createTheater();
		List<TheaterDateDTO> theaterDateDTOList = new ArrayList<>();

		for (int i = 1; i <= 10; i++) {
			TheaterDateDTO theaterDateDTO = new TheaterDateDTO();
			theaterDateDTO.setScreeningDateTime(LocalDateTime.now());
			theaterDateDTO.setScreeningTime(ScreeningTime.MATINEE);

			TheaterDate theaterDate = theaterDateService.saveTheaterDate(theaterDateDTO, theater.getCode());
			//TODO
			theaterDateDTO.setCode(theaterDate.getCode());

			theaterDateDTOList.add(theaterDateDTO);
		}
		return theaterDateDTOList;
	}

	@Test
	@DisplayName("상영관 스케쥴 검색 테스트")
	public void findTheaterByTheaterCode() {
		this.createTheaterDateList();

		Long theaterCode = theaterRepository.findAll().get(0).getCode();
		List<TheaterDate> theaterDates = theaterDateRepository.findTheaterDateByTheaterCode(theaterCode);
		for (TheaterDate theaterDate : theaterDates) {
			System.out.println(theaterDate);
		}
	}

	@Test
	@DisplayName("상영관 스케쥴 삭제 테스트")
	public void deleteTheaterDate() {
		TheaterDateDTO theaterDateDTO = this.createTheaterDateList().get(4);

		Long theaterCode = theaterRepository.findAll().get(0).getCode();

		theaterDateService.deleteTheaterDate(theaterDateDTO.getCode());
		List<TheaterDate> theaterDates = theaterDateRepository.findTheaterDateByTheaterCode(theaterCode);
		for (TheaterDate theaterDate : theaterDates) {
			System.out.println(theaterDate);
		}
	}


}
