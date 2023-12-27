package com.HiBro.entity;

import com.HiBro.constant.*;
import com.HiBro.dto.TheaterDTO;
import lombok.*;

import javax.persistence.*;
import java.util.*;

@Getter
@Setter
@Entity
@Table(name = "theater")
public class Theater {
	@Id
	@Column(name = "theater_code")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long code;

	@Column(nullable = false)
	private String theaterName;

	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private Location location;

	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private TheaterStatus theaterStatus;

	@OneToMany(mappedBy = "theater", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
	private List<Screen> screens = new ArrayList<>();

	public static Theater createTheater(TheaterDTO theaterDTO) {
		Theater theater = new Theater();
		theater.setLocation(theaterDTO.getLocation());
		theater.setTheaterName(theaterDTO.getTheaterName());
		theater.setTheaterStatus(theaterDTO.getTheaterStatus());
		return theater;
	}
	public void updateTheater(TheaterDTO theaterDTO) {
		this.theaterName = theaterDTO.getTheaterName();
		this.location = theaterDTO.getLocation();
		this.theaterStatus = theaterDTO.getTheaterStatus();
	}

	@Override
	public String toString() {
		return "Theater{" +
				"code=" + code +
				", theaterName='" + theaterName + '\'' +
				", location=" + location +
				", theaterStatus=" + theaterStatus +
				'}';
	}
}
