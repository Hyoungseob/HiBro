package com.HiBro.repository;

import com.HiBro.entity.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import java.util.List;

public interface MovieRepository extends JpaRepository<Movie, Long>, QuerydslPredicateExecutor,  MovieChartRepository{

    Movie findByCode(Long code);

    List<Movie> findByMovieTitle(String movieTitle);
}
