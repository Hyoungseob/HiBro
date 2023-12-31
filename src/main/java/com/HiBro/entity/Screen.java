package com.HiBro.entity;

import com.HiBro.constant.ScreenType;
import com.HiBro.dto.ScreenDTO;
import lombok.*;

import javax.persistence.*;
import java.util.*;

@Entity
@Table(name = "screen")
@Getter
@Setter
public class Screen {
	@Id
	@Column(name = "screen_code")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long code;

	@Column(nullable = false)
	private String screenLocation;

	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private ScreenType screenType;

	@ManyToOne
	@JoinColumn(name = "theater_code")
	private Theater theater;

	@OneToMany(mappedBy = "screen", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
	private List<ScreenDate> screenDates = new ArrayList();

	public static Screen createScreen(ScreenDTO screenDTO) {
		Screen screen = new Screen();
		screen.setScreenLocation(screenDTO.getScreenLocation());
		screen.setScreenType(screenDTO.getScreenType());
		return screen;
	}

	public void updateScreen(ScreenDTO screenDTO) {
		this.screenLocation = screenDTO.getScreenLocation();
		this.screenType = screenDTO.getScreenType();
	}

	@Override
	public String toString() {
		return "Screen{" +
				"code=" + code +
				", screenLocation='" + screenLocation + '\'' +
				", screenType=" + screenType +
				'}';
	}
}
