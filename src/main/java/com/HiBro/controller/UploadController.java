package com.HiBro.controller;

import com.HiBro.dto.MovieDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class UploadController {

    @GetMapping("/admin/upload")
    public String movieUpload(Model model){
        model.addAttribute("movieDTO", new MovieDTO());
        return "administrator/movieUpload";
    }
}
