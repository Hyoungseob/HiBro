package com.HiBro.service;

import com.HiBro.dto.*;
import com.HiBro.entity.Theater;
import com.HiBro.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class TheaterService {
	private final TheaterRepository theaterRepository;
	private final ScreenService screenService;

	public Theater getTheater(Long theaterCode) {
		return theaterRepository.findByCode(theaterCode);
	}

	public List<Theater> getTheaterList() {
		return theaterRepository.findAll();
	}

	public void updateTheater(TheaterDTO theaterDTO) {
		Theater theater = theaterRepository.findByCode(theaterDTO.getCode());
		theater.updateTheater(theaterDTO);
	}

	public Theater saveTheater(TheaterDTO theaterDTO) {
		Theater theater = Theater.createTheater(theaterDTO);
		return theaterRepository.save(theater);
	}

	public void deleteTheater(Long theaterCode) {
		Theater theater = theaterRepository.findByCode(theaterCode);
		screenService.deleteScreenList(theaterCode);
		theaterRepository.delete(theater);
	}
}
