package com.HiBro.controller;

import com.HiBro.dto.MovieChartFormDTO;
import com.HiBro.repository.MovieRepository;
import com.HiBro.service.MovieService;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Log
@Controller
@RequiredArgsConstructor
public class MovieChartController {

    private final MovieService movieService;
    private final MovieRepository movieRepository;


    @GetMapping("/movieChart")
    public String movieChart(Model model){

        List<MovieChartFormDTO> movieChartFormDTOS = movieRepository.getMovieChartFormDTOList();
        model.addAttribute("MovieChartFormDTOList", movieChartFormDTOS);

        log.info(movieChartFormDTOS.get(0).toString());
        return "forum/movieChart";
    }
}
