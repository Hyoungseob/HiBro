package com.HiBro.service;

import com.HiBro.dto.SeatDTO;
import com.HiBro.entity.Seat;
import com.HiBro.entity.Theater;
import com.HiBro.entity.TheaterDate;
import com.HiBro.repository.SeatRepository;
import com.HiBro.repository.TheaterRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class SeatService {
	private final TheaterRepository theaterRepository;
	private final SeatRepository seatRepository;

	public Seat saveSeat(SeatDTO seatDTO, Long theaterCode) {
		Theater theater = theaterRepository.findByCode(theaterCode);
		Seat seat = Seat.createSeat(seatDTO);
		seat.setTheater(theater);
		return seatRepository.save(seat);
	}

	public void deleteSeat(Long seatCode) {
		Seat seat = seatRepository.findByCode(seatCode);
		seatRepository.delete(seat);
	}


}
