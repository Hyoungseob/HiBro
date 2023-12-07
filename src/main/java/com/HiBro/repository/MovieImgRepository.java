package com.HiBro.repository;

import com.HiBro.constant.ImgType;
import com.HiBro.entity.Movie;
import com.HiBro.entity.MovieImg;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MovieImgRepository extends JpaRepository<MovieImg, Long> {
   // List<MovieImg> findByMovieCodeOrderByMovieCodeAsc(Long code);

    MovieImg findByMovieCodeAndImgType(Long code, ImgType imgType);

}
