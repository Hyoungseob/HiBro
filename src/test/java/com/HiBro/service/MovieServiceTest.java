package com.HiBro.service;

import com.HiBro.constant.AgeLimit;
import com.HiBro.constant.ImgType;
import com.HiBro.constant.VideoType;
import com.HiBro.dto.MovieDTO;
import com.HiBro.dto.MovieImgDTO;
import com.HiBro.dto.MovieVideoDTO;
import com.HiBro.entity.Movie;
import com.HiBro.entity.MovieImg;
import com.HiBro.entity.MovieVideo;
import com.HiBro.repository.MovieImgRepository;
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
class MovieServiceTest {

    @Autowired
    MovieRepository movieRepository;
    @Autowired
    MovieImgRepository movieImgRepository;
    @Autowired
    MovieVideoRepository movieVideoRepository;
    @Autowired
    MovieService movieService;

    public MovieDTO 테스트용_영화데이터_생성DTO(){
        MovieDTO movieDTO = new MovieDTO();

        movieDTO.setMovieTitle("오펜하이머");
        movieDTO.setActor("킬리언 머피");
        movieDTO.setDirector("크리스토퍼 놀란");
        movieDTO.setGenre("드라마");
        movieDTO.setSummary("핵 연구시설");
        movieDTO.setAgeLimit(AgeLimit.FIFTEEN);

        movieRepository.save(Movie.createMovie(movieDTO));

        return movieDTO;
    }

    public MovieImgDTO 테스트용_영화이미지_데이터_생성DTO(){
        MovieImgDTO movieImgDTO = new MovieImgDTO();

        movieImgDTO.setImgType(ImgType.POSTER);
        movieImgDTO.setImgUrl("hi");
        movieImgDTO.setOriImgName("ㅎㅇ");
        movieImgDTO.setImgName("하이");

        Movie movie = movieRepository.save(Movie.createMovie(테스트용_영화데이터_생성DTO()));

        //영화 데이터에 영상 데이터 연결
        MovieImg movieImg = MovieImg.createMovieImg(movieImgDTO);
        movieImg.setMovie(movie);

        return movieImgDTO;
    }

    public MovieVideoDTO 테스트용_영화영상_데이터_생성DTO(){
        MovieVideoDTO movieVideoDTO = new MovieVideoDTO();


        movieVideoDTO.setVideoName("메인 예고편");
        movieVideoDTO.setOriVideoName("몰라몰라");
        movieVideoDTO.setVideoType(VideoType.TRAILER);
        movieVideoDTO.setVideoUrl("sdfagsadg");

        Movie movie = movieRepository.save(Movie.createMovie(테스트용_영화데이터_생성DTO()));

        //영화 데이터에 영상 데이터 연결
        MovieVideo movieVideo = MovieVideo.createMovieVideo(movieVideoDTO);
        movieVideo.setMovie(movie);

        return movieVideoDTO;

    }


    @Test
    void 영화서비스_저장기능_테스트() {

        MovieDTO movieDTO = 테스트용_영화데이터_생성DTO();
        MovieImgDTO movieImgDTO = 테스트용_영화이미지_데이터_생성DTO();
        MovieVideoDTO movieVideoDTO = 테스트용_영화영상_데이터_생성DTO();

        Movie movie =movieService.saveMovie(movieDTO, movieImgDTO, movieVideoDTO);

        System.out.println(movie);
        System.out.println(movieImgRepository.findByMovieCodeOrderByMovieCodeAsc(movie.getCode()));
        System.out.println(movieVideoRepository.findByMovieCode(movie.getCode()));

    }

    @Test
    void 영화서비스_삭제기능_테스트() {

        MovieDTO movieDTO = 테스트용_영화데이터_생성DTO();
        MovieImgDTO movieImgDTO = 테스트용_영화이미지_데이터_생성DTO();
        MovieVideoDTO movieVideoDTO = 테스트용_영화영상_데이터_생성DTO();

        Movie movie = movieService.saveMovie(movieDTO, movieImgDTO, movieVideoDTO);

        movieService.deleteMovie(movie.getCode());

        System.out.println(movie);
        System.out.println(movieImgRepository.findByMovieCodeOrderByMovieCodeAsc(movie.getCode()));
        System.out.println(movieVideoRepository.findByMovieCode(movie.getCode()));
    }


}