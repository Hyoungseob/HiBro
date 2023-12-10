package com.HiBro.service;

import com.HiBro.dto.ScreenDTO;
import com.HiBro.dto.TheaterDTO;
import com.HiBro.entity.*;
import com.HiBro.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class ScreenService {
	private final ScreenRepository screenRepository;
	private final TheaterRepository theaterRepository;
	private final SeatService seatService;
	private final ScreenDateService screenDateService;

	public Screen saveScreen(ScreenDTO screenDTO, Long theaterCode) {
		Screen screen = Screen.createScreen(screenDTO);
		Theater theater = theaterRepository.findByCode(theaterCode);
		screen.setTheater(theater);
		return screenRepository.save(screen);
	}

	public void deleteScreen(ScreenDTO screenDTO) {
		Screen screen = screenRepository.findByCode(screenDTO.getCode());
		seatService.deleteSeatList(screen);
		screenDateService.deleteScreenDateList(screen);
		screenRepository.delete(screen);
	}

	public void deleteScreenList(TheaterDTO theaterDTO) {
		List<Screen> screenList = screenRepository.findScreenByTheaterCode(theaterDTO.getCode());
		for (Screen screen : screenList) {
			seatService.deleteSeatList(screen);
			screenDateService.deleteScreenDateList(screen);
			screenRepository.delete(screen);
		}
	}
}
