package com.HiBro.repository;

import com.HiBro.constant.AgeLimit;
import com.HiBro.constant.ImgType;
import com.HiBro.constant.VideoType;
import com.HiBro.dto.MovieDTO;
import com.HiBro.dto.MovieVideoDTO;
import com.HiBro.entity.Movie;
import com.HiBro.entity.MovieImg;
import com.HiBro.entity.MovieVideo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import javax.transaction.Transactional;

import static org.junit.jupiter.api.Assertions.*;
@Transactional
@SpringBootTest
@TestPropertySource(locations = "classpath:application-test.properties")
class MovieVideoRepositoryTest {
    @Autowired
    MovieRepository movieRepository;
    @Autowired
    MovieVideoRepository movieVideoRepository;

    public Movie 테스트용_영화데이터_생성(){
        MovieDTO movieDTO = new MovieDTO();

        movieDTO.setMovieTitle("오펜하이머");
        movieDTO.setActor("킬리언 머피");
        movieDTO.setDirector("크리스토퍼 놀란");
        movieDTO.setGenre("드라마");
        movieDTO.setSummary("핵 연구시설");
        movieDTO.setAgeLimit(AgeLimit.FIFTEEN);

        return movieRepository.save(Movie.createMovie(movieDTO));
    }

    public MovieVideo 테스트용_영화영상_데이터_생성(){
        MovieVideoDTO movieVideoDTO = new MovieVideoDTO();

        movieVideoDTO.setVideoName("메인 예고편");
        movieVideoDTO.setOriVideoName("몰라몰라");
        movieVideoDTO.setVideoType(VideoType.TRAILER);
        movieVideoDTO.setVideoUrl("sdfagsadg");

        return movieVideoRepository.save(MovieVideo.createMovieVideo(movieVideoDTO));
    }

    @Test
    void 영화코드로_영상찾기_테스트() {
        테스트용_영화데이터_생성();
        테스트용_영화영상_데이터_생성();

        movieVideoRepository.findByMovieCode(테스트용_영화데이터_생성().getCode());
        movieVideoRepository.findByMovieCodeAndVideoType(테스트용_영화데이터_생성().getCode(),테스트용_영화영상_데이터_생성().getVideoType());
    }
}