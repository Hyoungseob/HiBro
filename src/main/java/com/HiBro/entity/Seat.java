package com.HiBro.entity;

import com.HiBro.constant.SeatStatus;
import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@Entity()
@Table(name = "seat")
@ToString
public class Seat {
	@Id
	@Column(name = "seat_code")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long SeatCode;

	@Column(nullable = false)
	private String SeatRow;

	@Column(nullable = false)
	private String SeatColumn;

	@Enumerated(EnumType.STRING)
	private SeatStatus seatStatus;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "theater_code")
	private Theater theater;

}
