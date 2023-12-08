package com.HiBro.service;

import com.HiBro.dto.SeatDTO;
import com.HiBro.entity.*;
import com.HiBro.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class SeatService {
	private final ScreenRepository screenRepository;
	private final SeatRepository seatRepository;

	public Seat saveSeat(SeatDTO seatDTO, Long theaterCode) {
		Screen screen = screenRepository.findByCode(theaterCode);
		Seat seat = Seat.createSeat(seatDTO);
		seat.setScreen(screen);
		return seatRepository.save(seat);
	}

	public void deleteSeat(Long seatCode) {
		Seat seat = seatRepository.findByCode(seatCode);
		seatRepository.delete(seat);
	}
}
