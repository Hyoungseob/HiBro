package com.HiBro.dto;

import com.HiBro.constant.ScreeningTime;
import com.HiBro.entity.Movie;
import com.HiBro.entity.ScreenDate;
import lombok.*;
import org.modelmapper.ModelMapper;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
public class ScreenDateDTO {
	private Long code;
	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
	private LocalDateTime screeningDateTime;
	private ScreeningTime screeningTime;
	private Movie movie;
	private static ModelMapper modelMapper = new ModelMapper();

	public static ScreenDateDTO of(ScreenDate screenDate) {
		return modelMapper.map(screenDate, ScreenDateDTO.class);
	}
}
