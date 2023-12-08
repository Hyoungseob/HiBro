package com.HiBro.service;

import com.HiBro.dto.ScreenDateDTO;
import com.HiBro.entity.*;
import com.HiBro.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class ScreenDateService {

	private final ScreenRepository screenRepository;
	private final ScreenDateRepository screenDateRepository;

	public ScreenDate saveScreenDate(ScreenDateDTO screenDateDTO, Long screenCode) {
		Screen screen = screenRepository.findByCode(screenCode);
		ScreenDate screenDate = ScreenDate.createScreenDate(screenDateDTO);
		screenDate.setScreen(screen);
		return screenDateRepository.save(screenDate);
	}

	public void deleteScreenDate(Long screenDateCode) {
		ScreenDate screenDate = screenDateRepository.findByCode(screenDateCode);
		screenDateRepository.delete(screenDate);
	}
}
