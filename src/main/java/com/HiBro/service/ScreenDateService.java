package com.HiBro.service;

import com.HiBro.dto.ScreenDateDTO;
import com.HiBro.entity.*;
import com.HiBro.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class ScreenDateService {

	private final ScreenRepository screenRepository;
	private final ScreenDateRepository screenDateRepository;
	private final SeatService seatService;

	public ScreenDate saveScreenDate(ScreenDateDTO screenDateDTO, Long screenCode) {
		Screen screen = screenRepository.findByCode(screenCode);
		ScreenDate screenDate = ScreenDate.createScreenDate(screenDateDTO);
		screenDate.setScreen(screen);
		return screenDateRepository.save(screenDate);
	}

	public void deleteScreenDate(ScreenDateDTO screenDateDTO) {
		ScreenDate screenDate = screenDateRepository.findByCode(screenDateDTO.getCode());
		seatService.deleteSeatList(screenDate);
		screenDateRepository.delete(screenDate);
	}

	public void deleteScreenDateList(Screen screen) {
		List<ScreenDate> screenDateList = screenDateRepository.findScreenDateByScreenCode(screen.getCode());
		for (ScreenDate screenDate : screenDateList) {
			seatService.deleteSeatList(screenDate);
			screenDateRepository.delete(screenDate);
		}
	}
}
