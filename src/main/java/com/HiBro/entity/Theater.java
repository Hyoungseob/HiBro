package com.HiBro.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "theater")
@Getter
@Setter
public class Theater {
	@Id
	@Column(name = "theater_code")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long code;

	@Column
	private String theaterImg;

	@Column
	private String theaterLocation;

	@Column
	private String theaterType;
}
