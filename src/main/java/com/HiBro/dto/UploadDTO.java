package com.HiBro.dto;

import com.HiBro.constant.AgeLimit;
import com.HiBro.entity.MovieImg;
import com.HiBro.entity.MovieVideo;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter @Setter
public class UploadDTO {

    private String movieTitle;
    private String actor;
    private String director;
    private String summary;
    private String genre;
    private AgeLimit ageLimit;
    private List<MovieImg> movieImgs = new ArrayList<>();
    private List<MovieVideo> movieVideos = new ArrayList<>();

}
