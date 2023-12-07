package com.HiBro.dto;

import com.HiBro.constant.ScreeningTime;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class TheaterDateDTO {
	private Long code;
	private LocalDateTime screeningDateTime;
	private ScreeningTime screeningTime;
}
