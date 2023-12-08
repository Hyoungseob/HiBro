package com.HiBro.dto;

import com.HiBro.constant.ScreenType;
import lombok.*;

@Getter
@Setter
public class ScreenDTO {
	private Long code;
	private String screenImg;
	private String screenLocation;
	private ScreenType screenType;
}
