package com.HiBro.repository;

import com.HiBro.constant.VideoType;
import com.HiBro.entity.MovieVideo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MovieVideoRepository extends JpaRepository<MovieVideo, Long> {

    List<MovieVideo> findByMovieCode(Long movieCode);

    MovieVideo findByMovieCodeAndVideoType(Long movieCode, VideoType videoType);
}
