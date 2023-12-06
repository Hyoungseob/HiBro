package com.HiBro.entity;

import com.HiBro.constant.ScreeningTime;
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
	private Long theaterDateCode;

	@Column(nullable = false)
	private LocalDateTime screeningDateTime;

	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private ScreeningTime screeningTime;

	@ManyToOne
	@JoinColumn(name = "theater_id")
	private Theater theater;
}
