package com.HiBro.dto;

import com.HiBro.constant.ScreenType;
import com.HiBro.entity.Screen;
import lombok.*;
import org.modelmapper.ModelMapper;

@Getter
@Setter
@ToString
public class ScreenDTO {
	private Long code;
	private String screenLocation;
	private ScreenType screenType;
	private static ModelMapper modelMapper = new ModelMapper();

	public static ScreenDTO of(Screen screen) {
		return modelMapper.map(screen, ScreenDTO.class);
	}
}
