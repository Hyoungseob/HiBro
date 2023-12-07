package com.HiBro.service;

import com.HiBro.dto.MovieDTO;
import com.HiBro.entity.Movie;
import com.HiBro.entity.MovieImg;
import com.HiBro.entity.MovieVideo;
import com.HiBro.repository.MovieImgRepository;
import com.HiBro.repository.MovieRepository;
import com.HiBro.repository.MovieVideoRepository;
import lombok.RequiredArgsConstructor;
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
    public Movie saveMovie(Movie movie){

        return movieRepository.save(movie);

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

        Movie movie = movieRepository.findByMovieCode(movieCode);

        if(checkMovie(movie)){
            movieRepository.delete(movie);
            movieImgService.deleteMovieImg(movie.getCode());
            movieVideoService.deleteMovieVideo(movie.getCode());
        }
    }
}
