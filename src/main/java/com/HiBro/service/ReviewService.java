package com.HiBro.service;

import com.HiBro.dto.MovieDTO;
import com.HiBro.dto.ReviewDTO;
import com.HiBro.entity.Member;
import com.HiBro.entity.Movie;
import com.HiBro.entity.Review;
import com.HiBro.repository.MemberRepository;
import com.HiBro.repository.MovieRepository;
import com.HiBro.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class ReviewService{

    private final MemberRepository memberRepository;
    private final MovieRepository movieRepository;
    private final ReviewRepository reviewRepository;
    public Review saveReview(ReviewDTO reviewDTO, String id, Long movieCode){
        Member member = memberRepository.findById(id);
        Movie movie = movieRepository.findById(movieCode)
                .orElseThrow(EntityNotFoundException::new);
        Review review = Review.createReview(reviewDTO,member,movie);
        return reviewRepository.save(review);
    }
}
