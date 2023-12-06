package com.HiBro.entity;


import com.HiBro.constant.ImgType;
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
    private String code;

    @Column(nullable = false)
    private String imgName;

    @Column(nullable = false)
    private String oriImgName;

    @Column(nullable = false)
    private String ImgUrl;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ImgType imgType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "movie_movie_code")
    private Movie movie;

    public void updateItemImg(String oriImgName, String imgName, String imgUrl){
        this.imgName = imgName;
        this.oriImgName = oriImgName;
        this.ImgUrl = imgUrl;
    }
}
