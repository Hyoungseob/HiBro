package com.HiBro.dto;

import com.HiBro.entity.Ticketing;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class TicketingDTO{

    private Long memberCode;

    private Long ticketingCode;

    private String movieTitle;

    private Long theaterCode;

    public TicketingDTO(Ticketing ticketing){
        this.memberCode = ticketing.getMember().getCode();
        this.ticketingCode = ticketing.getCode();
        this.movieTitle = ticketing.getMovie().getMovieTitle();
        this.theaterCode = ticketing.getTheater().getTheaterCode();
    }

}
