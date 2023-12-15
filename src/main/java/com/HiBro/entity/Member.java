package com.HiBro.entity;

import com.HiBro.constant.Role;
import com.HiBro.dto.AnswerDTO;
import com.HiBro.dto.MemberDTO;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

@Entity
@Table(name="member")
@Getter @Setter
@ToString
public class Member{
    @Id
    @Column(name="member_code")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long code;
    @Column(unique = true,nullable = false)
    private String id;
    @Column(nullable = false)
    private String password;
    @Column(nullable = false)
    private String name;
    @Column(unique = true,nullable = false)
    private String email;

    private Integer point;
    @Column(nullable = false)
    private LocalDate regDate;

    @Enumerated(EnumType.STRING)
    private Role role;

    private static ModelMapper modelMapper = new ModelMapper();
    public static Member createMember(MemberDTO memberDTO, PasswordEncoder passwordEncoder){
        Member member = new Member();
        member.setId(memberDTO.getId());
        member.setPassword(passwordEncoder.encode(memberDTO.getPassword()));
        member.setName(memberDTO.getName());
        member.setEmail(memberDTO.getEmail());
        member.setPoint(0);
        member.setRegDate(LocalDateTime.now().toLocalDate());
        member.setRole(memberDTO.getRole());
        return member;
    }
    public static MemberDTO of(Member member){
        if(member !=null){
            return modelMapper.map(member, MemberDTO.class);
        }
        return new MemberDTO();
    }
}
