package com.HiBro.service;

import com.HiBro.constant.AgeLimit;
import com.HiBro.constant.ImgType;
import com.HiBro.dto.MovieDTO;
import com.HiBro.dto.MovieImgDTO;
import com.HiBro.entity.Movie;
import com.HiBro.entity.MovieImg;
import com.HiBro.repository.MovieImgRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import javax.transaction.Transactional;

import java.util.List;


@SpringBootTest
@Transactional
@TestPropertySource(locations = "classpath:application-test.properties")
class MovieImgServiceTest {

    @Autowired
    MovieService movieService;

    @Autowired
    MovieImgService movieImgService;
    @Autowired
    MovieImgRepository movieImgRepository;

    public Movie 테스트용_영화데이터_생성(){
        MovieDTO movieDTO = new MovieDTO();

        movieDTO.setMovieTitle("오펜하이머");
        movieDTO.setActor("킬리언 머피");
        movieDTO.setDirector("크리스토퍼 놀란");
        movieDTO.setGenre("드라마");
        movieDTO.setSummary("핵 연구시설");
        movieDTO.setAgeLimit(AgeLimit.FIFTEEN);

        return movieService.saveMovie(Movie.createMovie(movieDTO));
    }

    public MovieImg 테스트용_영화이미지_데이터_생성(){
        MovieImgDTO movieImgDTO = new MovieImgDTO();

        movieImgDTO.setImgType(ImgType.POSTER);
        movieImgDTO.setImgUrl("hi");
        movieImgDTO.setOriImgName("ㅎㅇ");
        movieImgDTO.setImgName("하이");

        Movie movie = 테스트용_영화데이터_생성();

        //영화 데이터에 영상 데이터 연결
        MovieImg movieImg = MovieImg.createMovieImg(movieImgDTO);
        movieImg.setMovie(movie);

        return movieImg;
    }

    @Test
    void 영화이미지_저장_테스트() {

        MovieImg movieImg = movieImgService.saveMovieImg(테스트용_영화이미지_데이터_생성());
        System.out.println(movieImg);
    }

    @Test
    void 영화이미지_삭제_테스트() {

         for(int i=0; i<6; i++){
             movieImgRepository.save(테스트용_영화이미지_데이터_생성());
         }

         movieImgService.deleteMovieImg(1L);

        List<MovieImg> movieImgs = movieImgRepository.findByMovieCodeOrderByMovieCodeAsc(1L);
        System.out.println(movieImgs.size());
    }

}