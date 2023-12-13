package com.HiBro.service;

import com.HiBro.constant.AgeLimit;
import com.HiBro.dto.MovieDTO;
import com.HiBro.entity.Movie;
import com.HiBro.repository.MovieImgRepository;
import com.HiBro.repository.MovieRepository;
import com.HiBro.repository.MovieVideoRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.TestPropertySource;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
@Transactional
@TestPropertySource(locations = "classpath:application-test.properties")
class MovieServiceTest {

    @Autowired
    MovieRepository movieRepository;
    @Autowired
    MovieImgRepository movieImgRepository;
    @Autowired
    MovieVideoRepository movieVideoRepository;
    @Autowired
    MovieService movieService;
    @Autowired
    MovieImgService movieImgService;
    @Autowired
    MovieVideoService movieVideoService;

    List<MultipartFile> createImgMultipartFiles()throws Exception{

        List<MultipartFile> multipartFileList = new ArrayList<>();

        for(int i=0; i<5; i++){
            String path = movieImgService.getMovieImgLocation();
            String imageName = "image" + i + ".txt";

            MockMultipartFile multipartFile = new MockMultipartFile(path, imageName, "image/jpg",  new byte[]{1,2,3,4});
            multipartFileList.add(multipartFile);
        }
        return multipartFileList;
    }

    List<MultipartFile> createVideoMultipartFiles()throws Exception{

        List<MultipartFile> multipartFileList = new ArrayList<>();

        for(int i=0; i<5; i++){
            String path = movieVideoService.getMovieVideoLocation();
            String videoName = "video" + i + ".txt";

            MockMultipartFile multipartFile = new MockMultipartFile(path, videoName, "video/mp4",  new byte[]{1,2,3,4});
            multipartFileList.add(multipartFile);
        }
        return multipartFileList;
    }

    public Movie 테스트용_영화데이터_생성(){
        MovieDTO movieDTO = new MovieDTO();

        movieDTO.setMovieTitle("오펜하이머");
        movieDTO.setActor("킬리언 머피");
        movieDTO.setDirector("크리스토퍼 놀란");
        movieDTO.setGenre("드라마");
        movieDTO.setSummary("핵 연구시설");
        movieDTO.setAgeLimit(AgeLimit.FIFTEEN);

        return movieRepository.save(Movie.createMovie(movieDTO));
    }

    public List<MultipartFile> 테스트용_영화이미지_데이터_생성(Movie movie)throws Exception{

        List<MultipartFile> ImgmultipartFileList = createImgMultipartFiles();

        movieImgService.saveMovieImg(movie, ImgmultipartFileList);

        return ImgmultipartFileList;
    }

    public List<MultipartFile> 테스트용_영화영상_데이터_생성(Movie movie)throws Exception{

        List<MultipartFile> VodeomultipartFileList = createVideoMultipartFiles();

        movieVideoService.saveMovieVideo(movie, VodeomultipartFileList);

        return VodeomultipartFileList;

    }

    @Test
    void 영화서비스_저장기능_테스트() throws Exception{

        Movie movie = 테스트용_영화데이터_생성();
        테스트용_영화이미지_데이터_생성(movie);
        테스트용_영화영상_데이터_생성(movie);

        Movie reposiMovieData = movieRepository.findByCode(movie.getCode());

        Assertions.assertThat(reposiMovieData.getMovieTitle()).isEqualTo(movie.getMovieTitle());
        Assertions.assertThat(reposiMovieData.getActor()).isEqualTo(movie.getActor());
        Assertions.assertThat(reposiMovieData.getGenre()).isEqualTo(movie.getGenre());
        Assertions.assertThat(reposiMovieData.getDirector()).isEqualTo(movie.getDirector());
        Assertions.assertThat(reposiMovieData.getSummary()).isEqualTo(movie.getSummary());
        Assertions.assertThat(reposiMovieData.getAgeLimit()).isEqualTo(movie.getAgeLimit());

    }

    @Test
    void 영화서비스_삭제기능_테스트() throws Exception{

        Movie movie = 테스트용_영화데이터_생성();

        List<MultipartFile> movieImgList = 테스트용_영화이미지_데이터_생성(movie);
        List<MultipartFile> movieVideoList = 테스트용_영화영상_데이터_생성(movie);


        movieService.deleteMovie(movie.getCode());

        System.out.println(movie);
        System.out.println(movieImgRepository.findByMovieCodeOrderByMovieCodeAsc(movie.getCode()));
        System.out.println(movieVideoRepository.findByMovieCode(movie.getCode()));
    }


}