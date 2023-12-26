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
	private String location;

	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private ScreenType type;

	@ManyToOne
	@JoinColumn(name = "theater_code")
	private Theater theater;

	@OneToMany(mappedBy = "screen", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
	private List<ScreenDate> screenDates = new ArrayList();

	public static Screen createScreen(ScreenDTO screenDTO) {
		Screen screen = new Screen();
		screen.setLocation(screenDTO.getLocation());
		screen.setType(screenDTO.getType());
		return screen;
	}

	public void updateScreen(ScreenDTO screenDTO) {
		this.location = screenDTO.getLocation();
		this.type = screenDTO.getType();
	}

	@Override
	public String toString() {
		return "Screen{" +
				"code=" + code +
				", location='" + location + '\'' +
				", type=" + type +
				'}';
	}
}
