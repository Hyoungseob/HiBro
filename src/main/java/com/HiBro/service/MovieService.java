package com.HiBro.service;

import com.HiBro.dto.MovieDTO;
import com.HiBro.entity.Movie;
import com.HiBro.entity.MovieImg;
import com.HiBro.entity.MovieVideo;
import com.HiBro.repository.MovieImgRepository;
import com.HiBro.repository.MovieRepository;
import com.HiBro.repository.MovieVideoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class MovieService {

    private final MovieRepository movieRepository;

    public boolean checkMovie(Movie movie){

        if(movie != null){
            return true;
        }
        return false;

    }

    public Movie saveMovie(Long movieCode){

        Movie movie = movieRepository.findByMovieCode(movieCode);

        if(checkMovie(movie)){
            
        }

    }

}
