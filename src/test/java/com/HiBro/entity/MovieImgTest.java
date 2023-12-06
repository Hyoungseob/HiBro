package com.HiBro.entity;

import com.HiBro.constant.ImgType;
import com.HiBro.dto.MovieImgDTO;
import com.HiBro.repository.MovieImgRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import javax.transaction.Transactional;

@SpringBootTest
@Transactional
@TestPropertySource(locations = "classpath:application-test.properties")
public class MovieImgTest {

    @Autowired
    MovieImgRepository movieImgRepository;

    @Test
    public void MovieImgCreateTest(){

        MovieImgDTO movieImgDTO = new MovieImgDTO();

        movieImgDTO.setImgName("아바타 포스터");
        movieImgDTO.setOriImgName("123");
        movieImgDTO.setImgUrl("sdfskjfs");
        movieImgDTO.setImgType(ImgType.POSTER);

        MovieImg result = MovieImg.createMovieImg(movieImgDTO);

        movieImgRepository.findByMovieCodeAndImgType(result.getCode(), ImgType.POSTER);
        movieImgRepository.findByMovieCodeOrderByMovieCodeAsc(result.getCode());
    }

}
