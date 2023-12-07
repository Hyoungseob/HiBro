package com.HiBro.service;

import com.HiBro.constant.AgeLimit;
import com.HiBro.constant.VideoType;
import com.HiBro.dto.MovieDTO;
import com.HiBro.dto.MovieVideoDTO;
import com.HiBro.entity.Movie;
import com.HiBro.entity.MovieVideo;
import com.HiBro.repository.MovieRepository;
import com.HiBro.repository.MovieVideoRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import javax.transaction.Transactional;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
@Transactional
@TestPropertySource(locations = "classpath:application-test.properties")
class MovieVideoServiceTest {
    @Autowired
    MovieRepository movieRepository;
    @Autowired
    MovieVideoRepository movieVideoRepository;
    @Autowired
    MovieVideoService movieVideoService;

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
    void saveMovieVideo() {
        테스트용_영화데이터_생성();
        movieVideoService.saveMovieVideo(테스트용_영화영상_데이터_생성());
    }

    @Test
    void checkVideo() {
    }

    @Test
    void deleteMovieVideo() {
    }
}