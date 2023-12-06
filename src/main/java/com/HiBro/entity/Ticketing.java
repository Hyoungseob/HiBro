package com.HiBro.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Table(name = "ticketing")
@Getter @Setter @ToString
public class Ticketing {

    @Id
    @Column(name = "ticketing_code")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long code;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "theater_code")
    private Theater theater;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "movie_code")
    private Movie movie;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_code")
    private Member member;

    private Ticketing firstCreateTicketing(Member member){

        Ticketing ticketing = new Ticketing();

        ticketing.setMember(member);

        return ticketing;
    }

}
