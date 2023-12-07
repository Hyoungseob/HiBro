package com.HiBro.entity;

import com.HiBro.dto.TheaterDTO;
import lombok.*;

import javax.persistence.*;
import java.util.*;

@Entity
@Table(name = "theater")
@Getter
@Setter
@ToString
public class Theater {
	@Id
	@Column(name = "theater_code")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long code;

	@Column(nullable = false)
	private String theaterImg;

	@Column(nullable = false)
	private String theaterLocation;

	@Column(nullable = false)
	private String theaterType;

	//Order - OrderItem 관계랑 똑같음
	@OneToMany(mappedBy = "theater", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
	private List<Seat> seats = new ArrayList();

	@OneToMany(mappedBy = "theater", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
	private List<TheaterDate> theaterDates = new ArrayList();

	public static Theater createTheater(TheaterDTO theaterDTO) {
		Theater theater = new Theater();
		theater.setTheaterImg(theaterDTO.getTheaterImg());
		theater.setTheaterLocation(theaterDTO.getTheaterLocation());
		theater.setTheaterType(theaterDTO.getTheaterType());

		return theater;

	}
}
