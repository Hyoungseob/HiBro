package com.HiBro.entity;

import com.HiBro.constant.AgeLimit;
import com.HiBro.dto.MovieDTO;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Table(name="movie")
@Getter @Setter @ToString
public class Movie {

    @Id
    @Column(name="movie_code")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long code;

    @Column(unique = true, nullable = false)
    private String title;

    @Column(nullable = false)
    private String actor;

    @Column(nullable = false)
    private String director;

    @Column(nullable = false)
    private String summary;

    @Column(nullable = false)
    private String genre;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private AgeLimit ageLimit;

    public void updateMovie(MovieDTO movieDTO){
        this.title = movieDTO.getTitle();
        this.actor = movieDTO.getActor();
        this.director = movieDTO.getDirector();
        this.summary = movieDTO.getSummary();
        this.genre = movieDTO.getGenre();
        this.ageLimit = movieDTO.getAgeLimit();
    }
}
