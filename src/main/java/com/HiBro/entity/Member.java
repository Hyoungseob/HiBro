package com.HiBro.entity;

import com.HiBro.constant.Role;
import com.HiBro.dto.MemberDTO;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="member")
@Getter @Setter
public class Member{
    @Id
    @Column(name="member_code")
    private Long code;
    @Column(unique = true,nullable = false)
    private String id;
    @Column(nullable = false)
    private String password;
    @Column(nullable = false)
    private String name;
    @Column(unique = true,nullable = false)
    private String email;
    @Column(nullable = false)
    private Date regDate;

    @Enumerated(EnumType.STRING)
    private Role role;

    public static Member createMember(MemberDTO memberDTO, PasswordEncoder passwordEncoder){
        Member member = new Member();
        member.setId(memberDTO.getId());
        member.setPassword(passwordEncoder.encode(memberDTO.getPassword()));
        member.setName(memberDTO.getName());
        member.setRegDate(memberDTO.getRegDate());
        member.setRole(memberDTO.getRole());
        return member;
    }
}
