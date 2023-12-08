package com.HiBro.service;

import com.HiBro.dto.ScreenDTO;
import com.HiBro.entity.*;
import com.HiBro.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class ScreenService {
	private final ScreenRepository screenRepository;
	private final TheaterRepository theaterRepository;
	public Screen saveScreen(ScreenDTO screenDTO, Long code) {
		Screen screen = Screen.createScreen(screenDTO);
		Theater theater = theaterRepository.findByCode(code);
		screen.setTheater(theater);
		return screenRepository.save(screen);
	}

	public void deleteScreen(ScreenDTO screenDTO) {
		Screen screen = screenRepository.findByCode(screenDTO.getCode());
		screenRepository.delete(screen);
	}
}
