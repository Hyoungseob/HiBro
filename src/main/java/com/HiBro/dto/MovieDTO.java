package com.HiBro.dto;

import com.HiBro.constant.AgeLimit;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class MovieDTO {
    private Long code;
    private String title;
    private String actor;
    private String director;
    private String summary;
    private String genre;
    private AgeLimit ageLimit;
}
