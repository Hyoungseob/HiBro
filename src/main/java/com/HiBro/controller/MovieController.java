package com.HiBro.controller;

import com.HiBro.service.MovieService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class MovieController {

    private final MovieService movieService;

    @GetMapping(value="")
    public String movieChart(){

        return "movie/movieChart";
    }

}
