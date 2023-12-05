package com.HiBro.repository;

import com.HiBro.constant.AgeLimit;
import com.HiBro.entity.Movie;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import javax.transaction.Transactional;

@SpringBootTest
@Transactional
@TestPropertySource(locations = "classpath:application-test.properties")
public class MovieRepositoryTest {

    @Autowired
    MovieRepository movieRepository;

    @Test
    @DisplayName("영화 레파지토리 작동 테스트")
    public void saveMovie() {
        Movie movie = new Movie();

        movie.setMovieTitle("아바타");
        movie.setActor("조에 살다나");
        movie.setDirector("제임스 카메론");
        movie.setSummary("블라블라");
        movie.setGenre("판타지");
        movie.setAgeLimit(AgeLimit.TWELVE);

        movieRepository.save(movie);
    }
}
