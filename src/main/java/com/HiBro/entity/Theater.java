package com.HiBro.entity;

import com.HiBro.constant.*;
import com.HiBro.dto.TheaterDTO;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
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
	@NotNull
	private String name;

	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	@NotNull
	private Location location;

	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	@NotNull
	private TheaterStatus status;

	public static Theater createTheater(TheaterDTO theaterDTO) {
		Theater theater = new Theater();
		theater.setLocation(theaterDTO.getLocation());
		theater.setName(theaterDTO.getName());
		theater.setStatus(theaterDTO.getStatus());
		return theater;
	}
	public void updateTheater(TheaterDTO theaterDTO) {
		this.name = theaterDTO.getName();
		this.location = theaterDTO.getLocation();
		this.status = theaterDTO.getStatus();
	}

	@Override
	public String toString() {
		return "Theater{" +
				"code=" + code +
				", name='" + name + '\'' +
				", location=" + location +
				", status=" + status +
				'}';
	}
}
