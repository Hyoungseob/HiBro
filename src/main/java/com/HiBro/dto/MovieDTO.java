package com.HiBro.dto;

import com.HiBro.constant.AgeLimit;
import com.HiBro.entity.MovieImg;
import com.HiBro.entity.MovieVideo;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter @Setter
public class MovieDTO {

    private Long code;

    @NotBlank(message = "영화 제목은 필수 입력입니다.")
    private String movieTitle;

    @NotBlank(message = "주연 배우란은 필수 입력란입니다.")
    private String actor;

    @NotBlank(message = "영화 감독란은 필수 입력란입니다.")
    private String director;

    @NotBlank(message = "영화 줄거리는 필수 입력입니다.")
    private String summary;

    private String genre;

    private AgeLimit ageLimit;

    private LocalDate premiereDate;

    private List<MovieImg> movieImg = new ArrayList<>();

    private List<MovieVideo> movieVideos = new ArrayList<>();
}
