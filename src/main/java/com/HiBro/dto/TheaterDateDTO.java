package com.HiBro.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class TheaterDateDTO {
	private Long theaterDateCode;
	private LocalDateTime screeningDateTime;
}