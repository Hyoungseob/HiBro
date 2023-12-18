package com.HiBro.dto;

import com.HiBro.constant.ScreenType;
import lombok.*;

@Getter
@Setter
@ToString
public class ScreenDTO {
	private Long code;
	private String screenLocation;
	private ScreenType screenType;
}
