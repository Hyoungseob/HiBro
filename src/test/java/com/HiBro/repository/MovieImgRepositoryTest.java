package com.HiBro.repository;

import com.HiBro.constant.ImgType;
import com.HiBro.entity.MovieImg;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import javax.transaction.Transactional;

@SpringBootTest
@Transactional
@TestPropertySource(locations = "classpath:application-test.properties")
public class MovieImgRepositoryTest {

    MovieImgRepository movieImgRepository;

    @Test
    @DisplayName("영화 이미지 레파지토리 작동 테스트")
    public void saveMovieImg(){

        MovieImg movieImg = new MovieImg();

        movieImg.setImgName("포스터");
        movieImg.setOriImgName("1234");
        movieImg.setImgUrl("adsad");
        movieImg.setImgType(ImgType.POSTER);

        movieImgRepository.save(movieImg);
    }
}
