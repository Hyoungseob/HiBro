package com.HiBro.dto;

import com.HiBro.constant.AgeLimit;
import com.HiBro.entity.MovieImg;
import com.HiBro.entity.MovieVideo;
import com.querydsl.core.annotations.QueryProjection;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotBlank;
import java.time.LocalDate;
import java.util.List;

@ToString
@Getter @Setter
public class MovieChartFormDTO {

    private Long movieCode;

    @NotBlank(message = "영화 제목은 필수 입력입니다.")
    private String movieTitle;

    private LocalDate premiereDate;

    private String imgUrl;

    @QueryProjection
    public MovieChartFormDTO(Long movieCode, String movieTitle, LocalDate premiereDate, String imgUrl){
        this.movieCode = movieCode;
        this.movieTitle = movieTitle;
        this.premiereDate = premiereDate;
        this.imgUrl = imgUrl;
    }

}
