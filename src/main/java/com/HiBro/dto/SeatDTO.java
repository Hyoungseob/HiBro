package com.HiBro.dto;

import com.HiBro.constant.SeatStatus;
import com.HiBro.entity.Seat;
import com.HiBro.entity.Theater;
import lombok.*;
import org.modelmapper.ModelMapper;

@Getter
@Setter
public class SeatDTO {
	private Long code;
	private String seatColumn;
	private String seatRow;
	private SeatStatus seatStatus;
	private static ModelMapper modelMapper = new ModelMapper();

	public static SeatDTO of(Seat seat) {
		return modelMapper.map(seat, SeatDTO.class);
	}
}
