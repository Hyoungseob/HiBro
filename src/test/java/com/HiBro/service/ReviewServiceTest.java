package com.HiBro.service;

import com.HiBro.constant.AgeLimit;
import com.HiBro.constant.Role;
import com.HiBro.dto.MemberDTO;
import com.HiBro.dto.MovieDTO;
import com.HiBro.dto.ReviewDTO;
import com.HiBro.entity.Member;
import com.HiBro.entity.Movie;
import com.HiBro.entity.Review;
import com.HiBro.repository.MovieRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.TestPropertySource;

import javax.transaction.Transactional;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
@TestPropertySource(locations = "classpath:application-test.properties")
class ReviewServiceTest{
    @Autowired
    ReviewService reviewService;
    @Autowired
    MemberService memberService;
    @Autowired
    MovieRepository movieRepository;
    @Autowired
    PasswordEncoder encoder;
    public Member createMember(){
        MemberDTO memberDTO = new MemberDTO();

        memberDTO.setId("test");
        memberDTO.setPassword("1234");
        memberDTO.setName("테스트");
        memberDTO.setEmail("a@a");
        memberDTO.setRole(Role.USER);
        memberDTO.setRegDate(LocalDateTime.now());
        return Member.createMember(memberDTO,encoder);
    }
    public Movie createMovie(){
        MovieDTO movieDTO = new MovieDTO();

        movieDTO.setMovieTitle("오펜하이머");
        movieDTO.setActor("킬리언 머피");
        movieDTO.setDirector("크리스토퍼 놀란");
        movieDTO.setGenre("드라마");
        movieDTO.setSummary("핵 연구시설");
        movieDTO.setAgeLimit(AgeLimit.FIFTEEN);
        return Movie.createMovie(movieDTO);
    }
    public ReviewDTO createReviewDTO(){
        ReviewDTO reviewDTO = new ReviewDTO();
        reviewDTO.setGrade(10f);
        reviewDTO.setContent("리뷰 테스트");
        return reviewDTO;
    }
    @Test
    @DisplayName("리뷰 생성 테스트")
    public void createReview(){
        Member member = createMember();
        memberService.saveMember(member);

        Movie movie = createMovie();
        movieRepository.save(movie);

        ReviewDTO reviewDTO = createReviewDTO();
        Review review = reviewService.saveReview(reviewDTO,member.getId(),movie.getCode());
    }
}