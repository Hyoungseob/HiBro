package com.HiBro.repository;

import com.HiBro.entity.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MovieRepository extends JpaRepository<Movie, Long> {

    Movie findByCode(Long code);

    List<Movie> findByMovieTitle(String movieTitle);
}
