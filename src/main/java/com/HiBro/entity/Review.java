package com.HiBro.entity;

import com.HiBro.dto.ReviewDTO;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Table(name = "review")
@Getter @Setter @ToString
public class Review{
    @Id
    @Column(name = "review_code")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long code;
    @Column(nullable = false)
    private String content;
    @Column(nullable = false)
    private Float grade;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_code")
    private Member member;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "movie_code")
    private Movie movie;

    public static Review createReview(ReviewDTO reviewDTO, Member member, Movie movie){
        Review review = new Review();
        review.setContent(reviewDTO.getContent());
        review.setGrade(reviewDTO.getGrade());
        review.setMember(member);
        review.setMovie(movie);
        return review;
    }
}
