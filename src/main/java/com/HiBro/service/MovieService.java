package com.HiBro.service;

import com.HiBro.dto.MovieDTO;
import com.HiBro.entity.Movie;
import com.HiBro.repository.MovieRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class MovieService {

    private final MovieRepository movieRepository;

    private final MovieImgService movieImgService;

    private final MovieVideoService movieVideoService;

    //영화 객체 생성 및 id 값 생성
    public Movie saveMovie(MovieDTO movieDTO){

        Movie movie = movieRepository.save(Movie.createMovie(movieDTO));

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
    public Page<Movie> getMovieList(Pageable pageable){
        return movieRepository.findAll(pageable);
    }

    //무비 차트 더보기 기능 구현을 위한 Pageable 객체 생성
    public Pageable getMoviePage(Optional<Integer> moviePageCnt){

        Pageable moviePageable = PageRequest.of(moviePageCnt.isPresent() ? moviePageCnt.get() : 0 , 8);

        return moviePageable;
    }

}
