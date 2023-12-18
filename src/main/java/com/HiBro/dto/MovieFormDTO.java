package com.HiBro.dto;

import com.HiBro.constant.AgeLimit;
import com.HiBro.entity.MovieImg;
import com.HiBro.entity.MovieVideo;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import java.util.List;

@Getter @Setter
public class MovieFormDTO {

    private Long code;

    @NotBlank(message = "영화 제목은 필수 입력입니다.")
    private String movieTitle;

    @NotBlank(message = "영화 제목은 필수 입력입니다.")
    private String actor;

    @NotBlank(message = "영화 제목은 필수 입력입니다.")
    private String director;

    @NotBlank(message = "영화 제목은 필수 입력입니다.")
    private String summary;

    private String genre;

    private AgeLimit ageLimit;

    private List<MovieImg> movieImgs;

    private List<MovieVideo> movieVideos;
}
