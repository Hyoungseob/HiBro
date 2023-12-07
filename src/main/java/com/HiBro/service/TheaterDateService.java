package com.HiBro.service;

import com.HiBro.dto.TheaterDateDTO;
import com.HiBro.entity.Theater;
import com.HiBro.entity.TheaterDate;
import com.HiBro.repository.TheaterDateRepository;
import com.HiBro.repository.TheaterRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class TheaterDateService {

	private final TheaterRepository theaterRepository;
	private final TheaterDateRepository theaterDateRepository;

	public TheaterDate saveTheaterDate(TheaterDateDTO theaterDateDTO, Long theaterCode) {
		Theater theater = theaterRepository.findByCode(theaterCode);
		TheaterDate theaterDate = TheaterDate.createTheaterDate(theaterDateDTO);
		theaterDate.setTheater(theater);
		return theaterDateRepository.save(theaterDate);
	}

	public void deleteTheaterDate(Long theaterDateCode) {
		TheaterDate theaterDate = theaterDateRepository.findByCode(theaterDateCode);
		theaterDateRepository.delete(theaterDate);
	}
}
