package com.HiBro.entity;

import com.HiBro.constant.SeatStatus;
import com.HiBro.dto.SeatDTO;
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
	private Long code;

	@Column(nullable = false)
	private String seatRow;

	@Column(nullable = false)
	private String seatColumn;

	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private SeatStatus seatStatus;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "screen_date_code")
	private ScreenDate screenDate;

	public static Seat createSeat(SeatDTO seatDTO) {
		Seat seat = new Seat();
		seat.setSeatRow(seatDTO.getSeatRow());
		seat.setSeatColumn(seatDTO.getSeatColumn());
		seat.setSeatStatus(seatDTO.getSeatStatus());
		return seat;
	}
}
