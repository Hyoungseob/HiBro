package com.HiBro.entity;

import com.HiBro.constant.VideoType;
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
    private String code;

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
}
