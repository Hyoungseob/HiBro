package com.HiBro.service;

import com.HiBro.dto.MovieDTO;
import com.HiBro.dto.MovieImgDTO;
import com.HiBro.dto.MovieVideoDTO;
import com.HiBro.entity.Movie;
import com.HiBro.entity.MovieImg;
import com.HiBro.entity.MovieVideo;
import com.HiBro.repository.MovieImgRepository;
import com.HiBro.repository.MovieRepository;
import com.HiBro.repository.MovieVideoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class MovieService {

    private final MovieRepository movieRepository;

    private final MovieImgService movieImgService;

    private final MovieVideoService movieVideoService;

    //영화 DB저장
    public Movie saveMovie(MovieDTO movieDTO, MovieImgDTO movieImgDTO, MovieVideoDTO movieVideoDTO){

        //이미지, 영상 객체 생성
        MovieImg movieImg = MovieImg.createMovieImg(movieImgDTO);
        MovieVideo movieVideo = MovieVideo.createMovieVideo(movieVideoDTO);

        //영화 객체 생성
        Movie movie = movieRepository.save(Movie.createMovie(movieDTO));

        //영화 이미지와 영상에 FK 입력, 연관관계 완성
        movieImg.setMovie(movie);
        movieVideo.setMovie(movie);

        movieImgService.saveMovieImg(movieImg);
        movieVideoService.saveMovieVideo(movieVideo);

        return movie;

    }

    //영화 유무 체크
    public boolean checkMovie(Movie movie){

        if(movie != null){
            return true;
        }
        return false;

    }

    //특정 영화데이터 DB 삭제(영상,이미지 함께 삭제)
    public void deleteMovie(Long movieCode){

        Movie movie = movieRepository.findByCode(movieCode);

        if(checkMovie(movie)){
            movieVideoService.deleteMovieVideo(movie.getCode());
            movieImgService.deleteMovieImg(movie.getCode());
            movieRepository.delete(movie);
        }
    }
}
