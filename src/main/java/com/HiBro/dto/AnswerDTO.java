package com.HiBro.dto;

import com.HiBro.entity.Inquiry;
import com.HiBro.entity.Member;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class AnswerDTO{
    Long code;
    String content;
    Member member;
    Long inquiry_code;
}
