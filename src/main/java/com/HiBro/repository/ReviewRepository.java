package com.HiBro.repository;

import com.HiBro.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review,Long>{
    List<Review> findByMovieCode(Long movieCode);
    List<Review> findByMemberCode(Long memberCode);
    Review findByMemberCodeAndMovieCode(Long memberCode,Long movieCode);
}
