package com.HiBro.service;

import com.HiBro.dto.MovieDTO;
import com.HiBro.entity.Movie;
import com.HiBro.repository.MovieImgRepository;
import com.HiBro.repository.MovieRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class MovieService {

    @Value("")
    private String movieImgLocation;
    private final MovieImgRepository movieImgRepository;
    private final MovieRepository movieRepository;

    public List<Movie> findAll() {
        return movieRepository.findAll();
    }

    public Movie findByCode(Long code) {
        return movieRepository.findByCode(code);
    }
}
