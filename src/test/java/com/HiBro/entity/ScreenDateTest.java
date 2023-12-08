package com.HiBro.entity;

import com.HiBro.constant.ScreenType;
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
public class ScreenDateTest {

	@Autowired
	ScreenDateRepository screenDateRepository;
	@Autowired
	ScreenRepository screenRepository;
	@Autowired
	ScreenService screenService;
	@Autowired
	ScreenDateService screenDateService;

	public Screen createScreen() {
		ScreenDTO screenDTO = new ScreenDTO();
		screenDTO.setScreenImg("임시 이미지");
		screenDTO.setScreenLocation("울산 삼산동");
		screenDTO.setScreenType(ScreenType.NORMAL);

		Screen screen = screenService.saveScreen(screenDTO);

		return screen;
	}

	public List<ScreenDateDTO> createScreenDateList() {

		Screen screen = createScreen();
		List<ScreenDateDTO> screenDateDTOList = new ArrayList<>();

		for (int i = 1; i <= 10; i++) {
			ScreenDateDTO screenDateDTO = new ScreenDateDTO();
			screenDateDTO.setScreeningDateTime(LocalDateTime.now());
			screenDateDTO.setScreeningTime(ScreeningTime.MATINEE);

			ScreenDate screenDate = screenDateService.saveScreenDate(screenDateDTO, screen.getCode());
			//TODO
			screenDateDTO.setCode(screenDate.getCode());

			screenDateDTOList.add(screenDateDTO);
		}
		return screenDateDTOList;
	}

	@Test
	@DisplayName("상영관 스케쥴 검색 테스트")
	public void findScreenByScreenCode() {
		this.createScreenDateList();

		Long ScreenCode = screenRepository.findAll().get(0).getCode();
		List<ScreenDate> screenDates = screenDateRepository.findScreenDateByScreenCode(ScreenCode);
		for (ScreenDate screenDate : screenDates) {
			System.out.println(screenDate);
		}
	}

	@Test
	@DisplayName("상영관 스케쥴 삭제 테스트")
	public void deleteScreenDate() {
		ScreenDateDTO screenDateDTO = this.createScreenDateList().get(4);

		Long ScreenCode = screenRepository.findAll().get(0).getCode();

		screenDateService.deleteScreenDate(screenDateDTO.getCode());
		List<ScreenDate> screenDates = screenDateRepository.findScreenDateByScreenCode(ScreenCode);
		for (ScreenDate screenDate : screenDates) {
			System.out.println(screenDate);
		}
	}


}
