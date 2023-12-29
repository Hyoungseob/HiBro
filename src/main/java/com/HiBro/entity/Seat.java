package com.HiBro.entity;

import com.HiBro.constant.SeatStatus;
import com.HiBro.dto.SeatDTO;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

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
	@NotNull
	private String seatRow;

	@Column(nullable = false)
	@NotNull
	private String seatColumn;

	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	@NotNull
	private SeatStatus status;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "screen_date_code")
	private ScreenDate screenDate;

	public static Seat createSeat(SeatDTO seatDTO) {
		Seat seat = new Seat();
		seat.setSeatRow(seatDTO.getRow());
		seat.setSeatColumn(seatDTO.getColumn());
		seat.setStatus(seatDTO.getStatus());
		return seat;
	}

	public void updateSeat(SeatDTO seatDTO) {
		this.seatRow = seatDTO.getRow();
		this.seatColumn = seatDTO.getColumn();
		this.status = seatDTO.getStatus();
	}
}
