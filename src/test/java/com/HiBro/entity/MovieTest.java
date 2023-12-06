package com.HiBro.entity;

import com.HiBro.constant.AgeLimit;
import com.HiBro.dto.MovieDTO;
import com.HiBro.repository.MovieRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import javax.transaction.Transactional;
import java.util.List;

@SpringBootTest
@Transactional
@TestPropertySource(locations = "classpath:application-test.properties")
public class MovieTest {

    @Autowired
    MovieRepository movieRepository;

    @Test
    @DisplayName("영화 생성 테스트")
    public void createMovie(){

        MovieDTO movieDTO = new MovieDTO();

        movieDTO.setMovieTitle("오펜하이머");
        movieDTO.setActor("킬리언 머피");
        movieDTO.setDirector("크리스토퍼 놀란");
        movieDTO.setGenre("드라마");
        movieDTO.setSummary("핵 연구시설");
        movieDTO.setAgeLimit(AgeLimit.FIFTEEN);

        Movie movie = Movie.createMovie(movieDTO);
        movieRepository.save(movie);
        List<Movie> savedMovie = movieRepository.findByMovieTitle("오펜하이머");

        System.out.println(savedMovie.toString());

    }

}
