package com.HiBro.entity;


import com.HiBro.constant.ImgType;
import com.HiBro.dto.MovieImgDTO;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Table(name = "movie_img")
@Getter @Setter @ToString
public class MovieImg {

    @Id
    @Column(name="movie_img_code")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long code;

    @Column(nullable = false)
    private String imgName;

    @Column(nullable = false)
    private String oriImgName;

    @Column(nullable = false)
    private String imgUrl;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ImgType imgType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "movie_code")
    private Movie movie;

    public static MovieImg createMovieImg(MovieImgDTO movieImgDTO){

        MovieImg movieImg = new MovieImg();

        movieImg.setImgName(movieImgDTO.getImgName());
        movieImg.setOriImgName(movieImgDTO.getOriImgName());
        movieImg.setImgUrl(movieImgDTO.getImgUrl());
        movieImg.setImgType(movieImgDTO.getImgType());

        return movieImg;
    }


}
