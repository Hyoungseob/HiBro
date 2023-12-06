package com.HiBro.dto;

import com.HiBro.constant.VideoType;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class MovieVideoDTO {

    private Long code;

    private String videoName;

    private String oriVideoName;

    private String videoUrl;

    private VideoType videoType;
}
