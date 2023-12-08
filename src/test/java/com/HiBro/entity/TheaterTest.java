package com.HiBro.entity;

import com.HiBro.constant.ScreenType;
import com.HiBro.constant.TheaterStatus;
import com.HiBro.dto.ScreenDTO;
import com.HiBro.dto.TheaterDTO;
import com.HiBro.repository.ScreenRepository;
import com.HiBro.repository.TheaterRepository;
import com.HiBro.service.ScreenService;
import com.HiBro.service.TheaterService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

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
	ScreenRepository screenRepository;

	public ScreenDTO createScreen(TheaterDTO theaterDTO) {
		ScreenDTO screenDTO = new ScreenDTO();
		screenDTO.setScreenImg("임시 이미지");
		screenDTO.setScreenLocation("울산 삼산동");
		screenDTO.setScreenType(ScreenType.NORMAL);

		screenService.saveScreen(screenDTO, theaterDTO.getCode());

		return screenDTO;
	}

	public List<TheaterDTO> createTheaterList() {
		List<TheaterDTO> theaterDTOList = new ArrayList<>();

		for (int i = 0; i < 10; i++) {
			TheaterDTO theaterDTO = new TheaterDTO();
			theaterDTO.setTheaterLocation("울산 남구 삼산동");
			theaterDTO.setTheaterStatus(TheaterStatus.OPEN);

			Theater theater = theaterService.saveTheater(theaterDTO);

			theaterDTO.setCode(theater.getCode());

			theaterDTOList.add(theaterDTO);
		}
		return theaterDTOList;
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
		ScreenDTO screenDTO = this.createScreen(theaterDTO);
		Screen screen = screenService.saveScreen(screenDTO, theaterDTO.getCode());
		theaterService.deleteTheater(theaterDTO, screenDTO);

		List<Theater> theaterList = theaterRepository.findAll();
		List<Screen> screenList = screenRepository.findAll();

		for (Screen screens : screenList) {
			System.out.println(screen);
		}

		for (Theater theater : theaterList) {
			System.out.println(theater);
		}
	}


}
