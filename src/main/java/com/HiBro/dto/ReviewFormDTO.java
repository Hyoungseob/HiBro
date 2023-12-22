package com.HiBro.dto;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class ReviewFormDTO{
    private Long code;
    private String movieTitle;
    private String content;
    private String memberId;
}
