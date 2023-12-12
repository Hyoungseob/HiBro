package com.HiBro.entity;

import com.HiBro.constant.TheaterStatus;
import com.HiBro.dto.TheaterDTO;
import lombok.*;

import javax.persistence.*;
import java.util.*;

@Getter
@Setter
@Entity
@Table(name = "theater")
@ToString
public class Theater {
	@Id
	@Column(name = "theater_code")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long code;

	@Column(nullable = false)
	private String theaterLocation;

	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private TheaterStatus theaterStatus;

	@OneToMany(mappedBy = "theater", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
	private List<Screen> screens = new ArrayList<>();

	public static Theater createTheater(TheaterDTO theaterDTO) {
		Theater theater = new Theater();
		theater.setTheaterLocation(theaterDTO.getTheaterLocation());
		theater.setTheaterStatus(theaterDTO.getTheaterStatus());
		return theater;
	}
	public void updateTheater(TheaterDTO theaterDTO) {
		this.theaterLocation = theaterDTO.getTheaterLocation();
		this.theaterStatus = theaterDTO.getTheaterStatus();
	}
}
