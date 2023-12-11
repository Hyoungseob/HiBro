package com.HiBro.dto;

import com.HiBro.constant.SeatStatus;
import lombok.*;

@Getter
@Setter
public class SeatDTO {
	private Long code;
	private String seatColumn;
	private String seatRow;
	private SeatStatus seatStatus;
}
