package com.HiBro.dto;

import com.HiBro.constant.*;
import com.HiBro.entity.Theater;
import lombok.*;
import org.modelmapper.ModelMapper;

@Getter
@Setter
@ToString
public class TheaterDTO {
	private Long code;
	private String name;
	private Location location;
	private TheaterStatus status;
	private static ModelMapper modelMapper = new ModelMapper();

	public static TheaterDTO of(Theater theater) {
		return modelMapper.map(theater, TheaterDTO.class);
	}
}
