package com.HiBro.service;

import com.HiBro.dto.MovieDTO;
import com.HiBro.dto.ReviewDTO;
import com.HiBro.dto.ReviewFormDTO;
import com.HiBro.entity.Member;
import com.HiBro.entity.Movie;
import com.HiBro.entity.Review;
import com.HiBro.repository.MemberRepository;
import com.HiBro.repository.MovieRepository;
import com.HiBro.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class ReviewService{

    private final MemberRepository memberRepository;
    private final MovieRepository movieRepository;
    private final ReviewRepository reviewRepository;

    //리뷰 쓸수있는지 체크하는 함수 만들어서
    //체크하고 saveReview를 부르게 만들어야함
    public Review saveReview(ReviewDTO reviewDTO, String id, Long movieCode){
        Member member = memberRepository.findById(id);
        Movie movie = movieRepository.findById(movieCode)
                .orElseThrow(EntityNotFoundException::new);
        Review review = Review.createReview(reviewDTO,member,movie);
        return reviewRepository.save(review);
    }

    public Page<ReviewFormDTO> getMovieReviewList(Long movieCode, Pageable pageable){
        List<Review> reviewList=reviewRepository.findByMovieCode(movieCode);
        List<ReviewFormDTO> reviewFormDTOList = new ArrayList<>();
        for(Review review : reviewList){
            ReviewFormDTO reviewFormDTO = new ReviewFormDTO();
            reviewFormDTO.setCode(review.getCode());
            reviewFormDTO.setMovieTitle(review.getMovie().getMovieTitle());
            reviewFormDTO.setContent(review.getContent());
            reviewFormDTO.setMemberId(review.getMember().getId());
            reviewFormDTOList.add(reviewFormDTO);
        }

        return new PageImpl<>(reviewFormDTOList,pageable, reviewRepository.countByMovieCode(movieCode));
    }
    public List<Review> getMyReviewList(String memberId){
        Member member = memberRepository.findById(memberId);

        return reviewRepository.findByMemberCode(member.getCode());
    }
    public Review getMovieMyReview(Long movieCode,String memberId){
        Member member = memberRepository.findById(memberId);
        return reviewRepository.findByMemberCodeAndMovieCode(member.getCode(),movieCode);
    }
    public Review updateReview(ReviewDTO reviewDTO){
        Review review = reviewRepository.findById(reviewDTO.getCode()).get();
        review.setGrade(reviewDTO.getGrade());
        review.setContent(reviewDTO.getContent());
        return review;
    }
    public void deleteReview(Long reviewCode){
        Review review = reviewRepository.findById(reviewCode)
                .orElseThrow(EntityNotFoundException::new);
        reviewRepository.delete(review);
    }
    public Float getMovieGrade(Long movieCode){
        List<Review> reviewList = reviewRepository.findByMovieCode(movieCode);
        Float avg=0f;
        for(Review review : reviewList){
            avg +=review.getGrade();
        }
        avg /= reviewList.size();
        avg = Math.round(avg*10f)/10.0f;

        return avg;
    }
}
