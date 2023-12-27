package com.HiBro.entity;

import com.HiBro.constant.*;
import com.HiBro.dto.*;
import com.HiBro.repository.ScreenRepository;
import com.HiBro.service.*;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.TestPropertySource;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.*;

@SpringBootTest
@Transactional
@TestPropertySource(locations = "classpath:application-test.properties")
public class ScreenTest {
	@Autowired
	ScreenRepository screenRepository;
	@Autowired
	ScreenService screenService;
	@Autowired
	TheaterService theaterService;

	public TheaterDTO createTheater() {
		TheaterDTO theaterDTO = new TheaterDTO();
//		theaterDTO.setTheaterLocation("울산 남구 삼산동");
		theaterDTO.setTheaterStatus(TheaterStatus.OPEN);

		Theater theater = theaterService.saveTheater(theaterDTO);

		theaterDTO.setCode(theater.getCode());

		return theaterDTO;
	}

	public List<ScreenDTO> createScreenList() {
		List<ScreenDTO> screenDTOList = new ArrayList<>();
		TheaterDTO theaterDTO = this.createTheater();
		for (int i = 1; i <= 10; i++) {
			ScreenDTO screenDTO = new ScreenDTO();
//			screenDTO.setScreenImg("임시 이미지");
			screenDTO.setScreenLocation("울산 삼산동" + i);
			screenDTO.setScreenType(ScreenType.NORMAL);

			Screen screen = screenService.saveScreen(screenDTO, theaterDTO.getCode());

			screenDTO.setCode(screen.getCode());

			screenDTOList.add(screenDTO);
		}
		return screenDTOList;
	}

	@Test
	@DisplayName("상영관 삭제 테스트")
	public void deleteScreen() {
		ScreenDTO screenDTO = this.createScreenList().get(3);
		screenService.deleteScreen(screenDTO.getCode());

		List<Screen> screenList = screenRepository.findAll();

		for (Screen screen : screenList) {
			System.out.println(screen);
		}
	}
}
