package com.HiBro.dto;

import com.HiBro.constant.InquiryStatus;
import com.HiBro.entity.Inquiry;
import lombok.Getter;
import lombok.Setter;
import org.modelmapper.ModelMapper;

import javax.validation.constraints.NotEmpty;
import java.time.LocalDateTime;

@Getter @Setter
public class InquiryDTO{
    private Long code;
    @NotEmpty(message = "제목 입력은 필수 입니다.")
    private String title;
    @NotEmpty(message = "내용 입력은 필수 입니다.")
    private String content;
    private LocalDateTime regDate;
    private InquiryStatus inquiryStatus;

    private static ModelMapper modelMapper = new ModelMapper();

    public static InquiryDTO of(Inquiry inquiry){
        return modelMapper.map(inquiry, InquiryDTO.class);
    }
}
