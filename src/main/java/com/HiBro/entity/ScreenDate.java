package com.HiBro.entity;

import com.HiBro.constant.ScreeningTime;
import com.HiBro.dto.ScreenDateDTO;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "screen_date")
@Getter
@Setter
public class ScreenDate {
	@Id
	@Column(name = "screen_date_code")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long code;

	@Column(nullable = false)
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@NotNull
	private LocalDateTime screeningDateTime;

	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private ScreeningTime screeningTime;

	@ManyToOne
	@JoinColumn(name = "screen_code")
	@NotNull
	private Screen screen;

	@ManyToOne
	@JoinColumn(name = "movie_code")
	@NotNull
	private Movie movie;

	@OneToMany(mappedBy = "screenDate", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
	private List<Seat> seats = new ArrayList();


	public static ScreenDate createScreenDate(ScreenDateDTO screenDateDTO, Movie movie, Screen screen) {
		ScreenDate screenDate = new ScreenDate();
		screenDate.setScreeningDateTime(screenDateDTO.getScreeningDateTime());
		screenDate.setScreeningTime(screenDateDTO.getScreeningTime());
		screenDate.setMovie(movie);
		screenDate.setScreen(screen);
		return screenDate;
	}

	public void updateScreenDate(ScreenDateDTO screenDateDTO) {
		this.screeningDateTime = screenDateDTO.getScreeningDateTime();
		this.screeningTime = screenDateDTO.getScreeningTime();
		this.movie = screenDateDTO.getMovie();
	}

	@Override
	public String toString() {
		return "ScreenDate{" +
				"code=" + code +
				", screeningDateTime=" + screeningDateTime +
				", screeningTime=" + screeningTime +
				", screen=" + screen +
				'}';
	}
}
