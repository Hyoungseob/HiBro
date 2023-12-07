package com.HiBro.entity;

import com.HiBro.constant.ScreeningTime;
import com.HiBro.dto.TheaterDateDTO;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "theater_date")
@Getter
@Setter
@ToString
public class TheaterDate {
	@Id
	@Column(name = "theater_date_code")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long code;

	@Column(nullable = false)
	private LocalDateTime screeningDateTime;

	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private ScreeningTime screeningTime;

	@ManyToOne
	@JoinColumn(name = "theater_code")
	private Theater theater;

	public static TheaterDate createTheaterDate(TheaterDateDTO theaterDateDTO) {
		TheaterDate theaterDate = new TheaterDate();
		theaterDate.setScreeningDateTime(theaterDateDTO.getScreeningDateTime());
		theaterDate.setScreeningTime(theaterDateDTO.getScreeningTime());

		return theaterDate;
	}
}
