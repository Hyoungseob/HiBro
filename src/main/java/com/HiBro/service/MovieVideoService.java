package com.HiBro.service;

import com.HiBro.entity.MovieVideo;
import com.HiBro.repository.MovieVideoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class MovieVideoService {

    @Autowired
    MovieVideoRepository movieVideoRepository;

    public MovieVideo saveMovieVideo(MovieVideo movieVideo){

        return movieVideoRepository.save(movieVideo);

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
            }
        }
    }
}
