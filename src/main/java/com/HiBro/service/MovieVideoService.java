package com.HiBro.service;

import com.HiBro.entity.Movie;
import com.HiBro.entity.MovieVideo;
import com.HiBro.repository.MovieVideoRepository;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.thymeleaf.util.StringUtils;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.io.IOException;
import java.util.List;

@Log
@Service
@Getter @Setter
@Transactional
@RequiredArgsConstructor
public class MovieVideoService {

    @Value("${movieVideoLocation}")
    private String movieVideoLocation;
    private final MovieVideoRepository movieVideoRepository;

    private final FileService fileService;

    //동영상 저장
    public Long saveMovieVideo(Movie movie, List<MultipartFile> movieVideoFile){

        MovieVideo movieVideo = MovieVideo.createMovieVideo();
        movieVideo.setMovie(movie);

        for(MultipartFile movieVideoFileSaved :  movieVideoFile){

            saveMovieVideoFile(movieVideo, movieVideoFileSaved);
        }

        return movie.getCode();
    }

    //동영상 파일 저장
    //TODO 컨트롤러 및 뷰에서 Type 지정할 것
    public void saveMovieVideoFile(MovieVideo movieVideo, MultipartFile movieVideoFile){

        String oriVideoName = movieVideoFile.getOriginalFilename();
        String videoName = "";
        String videoUrl = "";

        try {
            if(!StringUtils.isEmpty(oriVideoName)){
                videoName = fileService.uploadFile(movieVideoLocation, oriVideoName, movieVideoFile.getBytes());

                //Url 처리 하기
                videoUrl = "/videos/movie/" + videoName;

               movieVideo.updateMovieVideo(oriVideoName, videoName, videoUrl);
               movieVideoRepository.save(movieVideo);
            }
        }catch (IOException e) {
            //TODO 예외 처리
            log.info("비디오 파일 저장 과정에서 에러가 발생하였습니다.");
        }

    }

    public void updateMovieVideo(Long movieVideoCode, MultipartFile movieVideoFile){

        if(!movieVideoFile.isEmpty()){
            MovieVideo savedMovieVideo = movieVideoRepository.findById(movieVideoCode).orElseThrow(EntityNotFoundException::new);

            if(!StringUtils.isEmpty(savedMovieVideo.getVideoName())){
                fileService.deleteFile(movieVideoLocation + "/" + savedMovieVideo.getVideoName());
            }

            try {
                String oriVideoName = movieVideoFile.getOriginalFilename();
                String videoName = fileService.uploadFile(movieVideoLocation, oriVideoName, movieVideoFile.getBytes());

                //Url 처리
                String videoUrl = "/videos/movie/" + videoName;

                savedMovieVideo.updateMovieVideo(oriVideoName, videoName, videoUrl);
            }catch (IOException e){
                //TODO 예외 처리 할 것
                log.info("영상 파일 저장과정에서 에러가 발생하였습니다.");
            }
        }
    }

    //동영상 유무 체크
    public boolean checkVideo(List<MovieVideo> movieVideos){

        if(movieVideos != null){
            return true;
        }
        return false;
    }

    //동영상 일괄 삭제
    public void deleteMovieVideo(Long movieCode){
        List<MovieVideo> findByMovieCodes = movieVideoRepository.findByMovieCode(movieCode);

            for(MovieVideo movieVideo : findByMovieCodes){
                movieVideoRepository.delete(movieVideo);
                fileService.deleteFile(movieVideoLocation + "/" + movieVideo.getVideoName());
            }

    }
}
