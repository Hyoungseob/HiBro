package com.HiBro.service;

import com.HiBro.dto.ScreenDTO;
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
	private final ScreenDateRepository screenDateRepository;
	private final SeatRepository seatRepository;

	public Seat getSeat(Long seatCode) {
		return seatRepository.findByCode(seatCode);
	}

	public void updateSeat(SeatDTO seatDTO) {
		Seat seat = seatRepository.findByCode(seatDTO.getCode());
		seat.updateSeat(seatDTO);
	}

	public Seat saveSeat(SeatDTO seatDTO, Long screenDateCode) {
		ScreenDate screenDate = screenDateRepository.findByCode(screenDateCode);
		Seat seat = Seat.createSeat(seatDTO);
		seat.setScreenDate(screenDate);
		return seatRepository.save(seat);
	}

	public void deleteSeat(Long seatCode) {
		Seat seat = seatRepository.findByCode(seatCode);
		seatRepository.delete(seat);
	}

	public void deleteSeatList(ScreenDate screenDate) {
		List<Seat> seatList = seatRepository.findByScreenDateCode(screenDate.getCode());
		for (Seat seat : seatList) {
			seatRepository.delete(seat);
		}
	}

	public List<Seat> getSeatList(Long screenDateCode) {
		return seatRepository.findByScreenDateCode(screenDateCode);
	}
}
