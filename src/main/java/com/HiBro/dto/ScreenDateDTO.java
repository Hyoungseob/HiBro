package com.HiBro.dto;

import com.HiBro.constant.ScreeningTime;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Getter
@Setter
public class ScreenDateDTO {
	private Long code;
	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
	private LocalDateTime screeningDateTime;
	private ScreeningTime screeningTime;
}
