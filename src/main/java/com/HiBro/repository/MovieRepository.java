package com.HiBro.repository;

import com.HiBro.entity.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MovieRepository extends JpaRepository<Movie, Long> {

    List<Movie> findByMovieTitle(String movieTitle);
    Movie findByCode(Long code);
}
