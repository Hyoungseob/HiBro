package com.HiBro.dto;

import com.HiBro.constant.*;
import lombok.*;

@Getter
@Setter
public class TheaterDTO {
	private Long code;
	private String theaterName;
	private Location location;
	private TheaterStatus theaterStatus;
}
