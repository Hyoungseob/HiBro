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
    @GeneratedValue(strategy = GenerationType.AUTO)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "movie_movie_code")
    private String movieCode;

    @Column(nullable = false)
    private String imgName;

    @Column(nullable = false)
    private String oriImgName;

    @Column(nullable = false)
    private String ImgUrl;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ImgType imgType;

    public void updateItemImg(String oriImgName, String imgName, String imgUrl){
        this.imgName = imgName;
        this.oriImgName = oriImgName;
        this.ImgUrl = imgUrl;
    }
}
