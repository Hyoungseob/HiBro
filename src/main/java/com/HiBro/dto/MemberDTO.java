package com.HiBro.dto;

import com.HiBro.constant.Role;
import lombok.*;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.*;
import java.time.LocalDate;

@Getter @Setter @ToString
public class MemberDTO{
    private Long code;
    @NotEmpty(message = "아이디 입력은 필수입니다.")
    @Length(min=4,max=12,message = "아이디는 최소 4자에서 최대 12자까지 입력 가능합니다.")
    private String id;
    @NotEmpty(message = "비밀번호 입력은 필수입니다.")
    @Length(min=4,max=16,message = "비밀번호는 최소 4자에서 최대 16자까지 입력 가능합니다.")
    private String password;
    @NotEmpty(message = "이름 입력은 필수입니다.")
    private String name;
    @NotEmpty(message = "이메일 입력은 필수입니다.")
    @Email(message = "이메일 형식으로 입력해주세요.")
    private String email;
    private Integer point;
    private LocalDate regDate;
    private Role role;
}
