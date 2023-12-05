package com.HiBro.entity;

import com.HiBro.constant.ScreeningTime;
import com.HiBro.dto.TheaterDTO;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "theater")
@Getter
@Setter
@ToString
public class Theater {
	@Id
	@Column(name = "theater_code")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long theaterCode;

	@Column(nullable = false)
	private String theaterImg;

	@Column(nullable = false)
	private String theaterLocation;

	@Column(nullable = false)
	private String theaterType;

	@OneToMany(mappedBy = "theater", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
	private List<Seat> seatList = new ArrayList();

	public static Theater createTheater(TheaterDTO theaterDTO) {
		Theater theater = new Theater();
		theater.setTheaterImg(theaterDTO.getTheaterImg());
		theater.setTheaterLocation(theaterDTO.getTheaterLocation());
		theater.setTheaterType(theaterDTO.getTheaterType());

		return theater;

	}
}
