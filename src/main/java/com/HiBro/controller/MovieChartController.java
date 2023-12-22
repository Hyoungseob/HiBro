package com.HiBro.controller;

import com.HiBro.dto.MovieChartFormDTO;
import com.HiBro.repository.MovieRepository;
import com.HiBro.service.MovieService;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Log
@Controller
@RequiredArgsConstructor
public class MovieChartController {

    private final MovieService movieService;
    private final MovieRepository movieRepository;

    @GetMapping("/movieChart")
    public String getMovieChart(Model model, Optional<Integer> moviePageCnt , String searchMovieTitle){

        Page<MovieChartFormDTO> movieChartFormDTOS = movieRepository.getMovieChartFormDTOList(moviePageCnt, searchMovieTitle);
        model.addAttribute("MovieChartFormDTOList", movieChartFormDTOS);

        return "forum/movieChart";
    }

    @PostMapping("/movieChart")
    public @ResponseBody ResponseEntity getMovieChartMore(Optional<Integer> movieChartCnt, Optional<String> searchMovieTitle){

        log.info(String.valueOf(movieChartCnt.orElse(0)));

        Page<MovieChartFormDTO> movieChartFormDTOList = movieRepository.getMovieChartFormDTOList(movieChartCnt, searchMovieTitle.orElse(""));
        return new ResponseEntity<Page<MovieChartFormDTO>>(movieChartFormDTOList, HttpStatus.OK);

    }
}
