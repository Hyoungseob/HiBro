package com.HiBro.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "Answer")
@Getter @Setter
public class Answer{
    @Id
    @Column(name = "answer_code")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long code;
    @Column(nullable = false)
    private String content;

    @ManyToOne
    @JoinColumn(name = "member_code")
    private Member member;

    @OneToOne
    @JoinColumn(name = "inquiry_code")
    private Inquiry inquiry;

}
