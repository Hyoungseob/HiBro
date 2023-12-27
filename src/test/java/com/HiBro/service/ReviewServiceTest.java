package com.HiBro.service;

import com.HiBro.constant.AgeLimit;
import com.HiBro.constant.Role;
import com.HiBro.dto.MemberDTO;
import com.HiBro.dto.MovieDTO;
import com.HiBro.dto.ReviewDTO;
import com.HiBro.entity.Member;
import com.HiBro.entity.Movie;
import com.HiBro.entity.Review;
import com.HiBro.repository.MemberRepository;
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
    MemberRepository memberRepository;
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
        memberDTO.setRegDate(LocalDateTime.now().toLocalDate());
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
    public void createReviews(){
        for(int i=1; i<=10;i++){
            MemberDTO memberDTO = new MemberDTO();

            memberDTO.setId("test"+i);
            memberDTO.setPassword("1234");
            memberDTO.setName("테스트"+i);
            memberDTO.setEmail("a@a"+i);
            memberDTO.setRole(Role.USER);
            memberDTO.setRegDate(LocalDateTime.now());

            Member member = memberService.saveMember(Member.createMember(memberDTO,encoder));

            MovieDTO movieDTO = new MovieDTO();

            movieDTO.setMovieTitle("영화제목"+i);
            movieDTO.setActor("배우"+i);
            movieDTO.setDirector("감독"+i);
            movieDTO.setGenre("장르");
            movieDTO.setSummary("줄거리"+i);
            movieDTO.setAgeLimit(AgeLimit.FIFTEEN);

            Movie movie = movieRepository.save(Movie.createMovie(movieDTO));
            ReviewDTO reviewDTO = createReviewDTO();
            Review review = reviewService.saveReview(reviewDTO,member.getId(),movie.getCode());
        }

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
    @Test
    @DisplayName("영화 기준 리뷰 리스트 가져오기 테스트")
    public void getMovieReviewList(){
        createReviews();

        List<Movie> movie =movieRepository.findByMovieTitle("영화제목1");
        List<Review> reviews = reviewService.getMovieReviewList(movie.get(0).getCode());

        System.out.println("-------- MovieReview -----------");
        for( Review i : reviews)
            System.out.println(i.toString());

    }
    @Test
    @DisplayName("멤버 기준 리뷰 리스트 가져오기 테스트")
    public void getMyReviewList(){
        createReviews();
        Member member = memberRepository.findById("test1");
        List<Review> reviews = reviewService.getMyReviewList(member.getCode());

        System.out.println("-------- MyReview -----------");
        for( Review i : reviews)
            System.out.println(i.toString());

    }
}