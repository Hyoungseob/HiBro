package com.HiBro.service;

import com.HiBro.constant.Location;
import com.HiBro.constant.ScreenType;
import com.HiBro.dto.ScreenDTO;
import com.HiBro.entity.*;
import com.HiBro.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.data.repository.query.Param;
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

	public Page<Screen> getScreenList(Long theaterCode, Pageable pageable) {
		return screenRepository.findByTheaterCode(theaterCode, pageable);
	}
	public Page<Screen> findByScreenType(ScreenType screenType, Pageable pageable) {
		return screenRepository.findByScreenType(screenType, pageable);
	}

	public Page<Screen> findByTheaterLocation(@Param("location") Location location, Pageable pageable) {
		return screenRepository.findByTheaterLocation(location, pageable);
	}

	public Page<Screen> findByScreenTypeAndTheaterLocation(@Param("screenType") ScreenType screenType, @Param("location") Location location, Pageable pageable) {
		return screenRepository.findByScreenTypeAndTheaterLocation(screenType, location, pageable);
	}

	public Page<Screen> findAll(Pageable pageable) {
		return screenRepository.findAll(pageable);
	}
}
