package com.HiBro.entity;

import com.HiBro.constant.AgeLimit;
import com.HiBro.dto.MovieDTO;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="movie")
@Getter @Setter @ToString
public class Movie {

    @Id
    @Column(name="movie_code")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long code;

    @Column(nullable = false)
    private String movieTitle;

    @Column(nullable = false)
    private String actor;

    @Column(nullable = false)
    private String director;

    @Lob
    @Column(nullable = false)
    private String summary;

    @Column(nullable = false)
    private String genre;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private AgeLimit ageLimit;

    /**@단방향 매핑 처리를 위한 양방향 리스트 주석 처리 */
    //@OneToMany(mappedBy = "movieCode", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    //private List<MovieImg> orderItems = new ArrayList<>();

    public static Movie createMovie(MovieDTO movieDTO){
        Movie movie = new Movie();

        movie.setMovieTitle(movieDTO.getMovieTitle());
        movie.setActor(movieDTO.getActor());
        movie.setDirector(movieDTO.getDirector());
        movie.setSummary(movieDTO.getSummary());
        movie.setGenre(movieDTO.getGenre());
        movie.setAgeLimit(movieDTO.getAgeLimit());

        return movie;
    }

    public void updateMovie(MovieDTO movieDTO){
        this.movieTitle = movieDTO.getMovieTitle();
        this.actor = movieDTO.getActor();
        this.director = movieDTO.getDirector();
        this.summary = movieDTO.getSummary();
        this.genre = movieDTO.getGenre();
        this.ageLimit = movieDTO.getAgeLimit();
    }


}
