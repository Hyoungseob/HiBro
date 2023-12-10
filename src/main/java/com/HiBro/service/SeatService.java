package com.HiBro.service;

import com.HiBro.dto.SeatDTO;
import com.HiBro.entity.*;
import com.HiBro.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class SeatService {
	private final ScreenRepository screenRepository;
	private final SeatRepository seatRepository;

	public Seat saveSeat(SeatDTO seatDTO, Long screenCode) {
		Screen screen = screenRepository.findByCode(screenCode);
		Seat seat = Seat.createSeat(seatDTO);
		seat.setScreen(screen);
		return seatRepository.save(seat);
	}

	public void deleteSeat(SeatDTO seatDTO) {
		Seat seat = seatRepository.findByCode(seatDTO.getCode());
		seatRepository.delete(seat);
	}

	public void deleteSeatList(Screen screen) {
		List<Seat> seatList = seatRepository.findSeatByScreenCode(screen.getCode());
		for (Seat seat : seatList) {
			seatRepository.delete(seat);
		}
	}
}
