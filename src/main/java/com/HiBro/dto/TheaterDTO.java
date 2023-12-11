package com.HiBro.dto;

import com.HiBro.constant.TheaterStatus;
import lombok.*;

@Getter
@Setter
public class TheaterDTO {
	private Long code;
	private String theaterLocation;
	private TheaterStatus theaterStatus;
}
