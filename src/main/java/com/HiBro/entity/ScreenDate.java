package com.HiBro.entity;

import com.HiBro.constant.ScreeningTime;
import com.HiBro.dto.ScreenDateDTO;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "screen_date")
@Getter
@Setter
@ToString
public class ScreenDate {
	@Id
	@Column(name = "screen_date_code")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long code;

	@Column(nullable = false)
	private LocalDateTime screeningDateTime;

	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private ScreeningTime screeningTime;

	@ManyToOne
	@JoinColumn(name = "screen_code")
	private Screen screen;

	@OneToMany(mappedBy = "screenDate", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
	private List<Seat> seats = new ArrayList();


	public static ScreenDate createScreenDate(ScreenDateDTO screenDateDTO) {
		ScreenDate screenDate = new ScreenDate();
		screenDate.setScreeningDateTime(screenDateDTO.getScreeningDateTime());
		screenDate.setScreeningTime(screenDateDTO.getScreeningTime());
		return screenDate;
	}

	public void updateScreenDate(ScreenDateDTO screenDateDTO) {
		this.screeningDateTime = screenDateDTO.getScreeningDateTime();
		this.screeningTime = screenDateDTO.getScreeningTime();
	}
}
