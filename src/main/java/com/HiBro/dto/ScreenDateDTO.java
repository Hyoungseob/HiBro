package com.HiBro.dto;

import com.HiBro.constant.ScreeningTime;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
public class ScreenDateDTO {
	private Long code;
	private LocalDateTime screeningDateTime;
	private ScreeningTime screeningTime;
}
