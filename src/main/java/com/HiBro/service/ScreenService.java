package com.HiBro.service;

import com.HiBro.dto.ScreenDTO;
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
	private final ScreenDateService screenDateService;

	public Screen getScreen(Long screenCode) {
		return screenRepository.findByCode(screenCode);
	}

	public void updateScreen(ScreenDTO screenDTO) {
		Screen screen = screenRepository.findByCode(screenDTO.getCode());
		screen.updateScreen(screenDTO);
	}

	public Screen saveScreen(ScreenDTO screenDTO, Long theaterCode) {
		Screen screen = Screen.createScreen(screenDTO);
		Theater theater = theaterRepository.findByCode(theaterCode);
		screen.setTheater(theater);
		return screenRepository.save(screen);
	}

	public void deleteScreen(Long screenCode) {
		Screen screen = screenRepository.findByCode(screenCode);
		screenDateService.deleteScreenDateList(screen);
		screenRepository.delete(screen);
	}

	public void deleteScreenList(Long theaterCode) {
		List<Screen> screenList = screenRepository.findByTheaterCode(theaterCode);
		for (Screen screen : screenList) {
			screenDateService.deleteScreenDateList(screen);
			screenRepository.delete(screen);
		}
	}

	public List<Screen> getScreenList(Long theaterCode) {
		return screenRepository.findByTheaterCode(theaterCode);
	}
}
