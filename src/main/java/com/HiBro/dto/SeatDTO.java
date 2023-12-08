package com.HiBro.dto;

import com.HiBro.constant.SeatStatus;
import lombok.*;

@Getter
@Setter
public class SeatDTO {
	private Long code;
	private String SeatRow;
	private String SeatColumn;
	private SeatStatus seatStatus;
}
