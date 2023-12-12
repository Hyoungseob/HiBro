package com.HiBro.entity;

import com.HiBro.constant.VideoType;
import com.HiBro.dto.MovieVideoDTO;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Table(name = "movie_video")
@Getter @Setter @ToString
public class MovieVideo {

    @Id
    @Column(name="movie_video_code")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long code;

    @Column(nullable = false)
    private String videoName;

    @Column(nullable = false)
    private String oriVideoName;

    @Column(nullable = false)
    private String videoUrl;

    @Enumerated
    @Column(nullable = false)
    private VideoType videoType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "movie_code")
    private Movie movie;

    public static MovieVideo createMovieVideo(){
        return new MovieVideo();
    }

    public static MovieVideo createMovieVideo(MovieVideoDTO movieVideoDTO){

        MovieVideo movieVideo = new MovieVideo();

        movieVideo.setVideoName(movieVideoDTO.getVideoName());
        movieVideo.setOriVideoName(movieVideoDTO.getOriVideoName());
        movieVideo.setVideoUrl(movieVideoDTO.getVideoUrl());
        movieVideo.setVideoType(movieVideoDTO.getVideoType());

        return movieVideo;
    }

    public void updateMovieVideo(String oriVideoName, String videoName, String videoUrl){
        this.oriVideoName = oriVideoName;
        this.videoName = videoName;
        this.videoUrl = videoUrl;
    }
}
