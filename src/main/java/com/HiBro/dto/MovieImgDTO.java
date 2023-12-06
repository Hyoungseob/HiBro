package com.HiBro.dto;

import com.HiBro.constant.ImgType;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class MovieImgDTO {

    private String code;

    private String imgName;

    private String oriImgName;

    private String imgUrl;

    private ImgType imgType;

}
