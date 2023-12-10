package com.HiBro.service;

import com.HiBro.dto.*;
import com.HiBro.entity.Theater;
import com.HiBro.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class TheaterService {
	private final TheaterRepository theaterRepository;
	private final ScreenService screenService;

	public Theater saveTheater(TheaterDTO theaterDTO) {
		Theater theater = Theater.createTheater(theaterDTO);
		return theaterRepository.save(theater);
	}
	public void deleteTheater(TheaterDTO theaterDTO, ScreenDTO screenDTO) {
		screenService.deleteScreen(screenDTO);

		Theater theater = theaterRepository.findByCode(theaterDTO.getCode());
		theaterRepository.delete(theater);
	}

}
