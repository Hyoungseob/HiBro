package com.HiBro.repository;

import com.HiBro.constant.AgeLimit;
import com.HiBro.constant.ImgType;
import com.HiBro.entity.Movie;
import com.HiBro.entity.MovieImg;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import javax.transaction.Transactional;

@SpringBootTest
@Transactional
@TestPropertySource(locations = "classpath:application-test.properties")
public class MovieImgRepositoryTest {

    @Autowired
    MovieImgRepository movieImgRepository;
    @Autowired
    MovieRepository movieRepository;

    @Test
    @DisplayName("영화 이미지 레파지토리 작동 테스트")
    public void saveMovieImg(){

        Movie movie = new Movie();

        movie.setMovieTitle("태극기 휘날리며");
        movie.setActor("장동건");
        movie.setDirector("모름");
        movie.setSummary("블라블라");
        movie.setGenre("액션");
        movie.setAgeLimit(AgeLimit.ADULT);

        Movie savedMovie = movieRepository.save(movie);

        MovieImg movieImg = new MovieImg();

        movieImg.setImgName("포스터");
        movieImg.setOriImgName("1234");
        movieImg.setImgUrl("adsad");
        movieImg.setImgType(ImgType.POSTER);
        movieImg.setMovie(savedMovie);

        movieImgRepository.save(movieImg);

        movieImgRepository.findByMovieCodeOrderByMovieCodeAsc(movie.getCode());
        movieImgRepository.findByMovieCodeAndImgType(movie.getCode(), ImgType.POSTER);
    }
}
