package com.HiBro.service;

import com.HiBro.entity.MovieImg;
import com.HiBro.entity.MovieVideo;
import com.HiBro.repository.MovieVideoRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
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
@Transactional
@RequiredArgsConstructor
public class MovieVideoService {

    @Value("${movieVideoLocation}")
    private String movieVideoLocation;
    MovieVideoRepository movieVideoRepository;

    private FileService fileService;

    //동영상 저장
    public void saveMovieVideo(MovieVideo movieVideo, MultipartFile movieVideoFile){

        String oriVideoName = movieVideoFile.getOriginalFilename();
        String videoName = "";
        String videoUrl = "";

        try {
            if(!StringUtils.isEmpty(oriVideoName)){
                videoName = fileService.uploadFile(movieVideoLocation, oriVideoName,movieVideoFile.getBytes());

                //Url 처리 하기
                videoUrl = "/videos/movie/" + videoName;

                movieVideo.setOriVideoName(oriVideoName);
                movieVideo.setVideoName(videoName);
                movieVideo.setVideoUrl(videoUrl);
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

        if(checkVideo(findByMovieCodes)){

            for(MovieVideo movieVideo : findByMovieCodes){
                movieVideoRepository.delete(movieVideo);
                fileService.deleteFile(movieVideoLocation + "/" + movieVideo.getVideoName());
            }
        }
    }
}
