package com.HiBro.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class AdminMovieController{

    @GetMapping("/admin/movie")
    public String getMovieList(){
        return "admin_movieList";
    }
}
