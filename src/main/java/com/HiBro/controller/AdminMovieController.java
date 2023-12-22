package com.HiBro.controller;

import com.HiBro.dto.ReviewFormDTO;
import com.HiBro.entity.Movie;
import com.HiBro.entity.Review;
import com.HiBro.service.MovieService;
import com.HiBro.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Controller
@Transactional
@RequiredArgsConstructor
public class AdminMovieController{
    private final MovieService movieService;
    private final ReviewService reviewService;
    @GetMapping("/admin/movie")
    public String getMovieList(Model model, Optional<Integer> page){
        Pageable pageable= PageRequest.of(page.orElse(0),10);
        Page<Movie> movieList = movieService.getMovieList(pageable);
        model.addAttribute("movieList",movieList);
        model.addAttribute("maxPage",5);
        return "administrator/admin_movieList";
    }
    @GetMapping("/admin/review/{movie_code}")
    public String getAnswerList(@PathVariable("movie_code")Long movieCode,Model model, Optional<Integer> page){
        Pageable pageable= PageRequest.of(page.orElse(0),10);
        Page<ReviewFormDTO> reviewList=reviewService.getMovieReviewList(movieCode,pageable);
        model.addAttribute("reviewList",reviewList);
        model.addAttribute("maxPage",5);
        return "administrator/admin_reviewList";
    }
}
