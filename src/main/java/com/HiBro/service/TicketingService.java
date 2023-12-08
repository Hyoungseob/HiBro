package com.HiBro.service;

import com.HiBro.entity.Member;
import com.HiBro.entity.Movie;
import com.HiBro.entity.Theater;
import com.HiBro.entity.Ticketing;
import com.HiBro.repository.MemberRepository;
import com.HiBro.repository.MovieRepository;
import com.HiBro.repository.TheaterRepository;
import com.HiBro.repository.TicketingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class TicketingService {

    MemberRepository memberRepository;

    MovieRepository movieRepository;

    TheaterRepository theaterRepository;

    TicketingRepository ticketingRepository;

    //예매 생성(멤버정보 확인)
    public Ticketing createTicketing(Long memberCode, Long movieCode, Long theaterCode){

        Ticketing ticketing = new Ticketing();

        Member member = memberRepository.findById(memberCode).orElseThrow(EntityNotFoundException::new);
        ticketing.setMember(member);

        Movie movie = movieRepository.findById(movieCode).orElseThrow(EntityNotFoundException::new);
        ticketing.setMovie(movie);

        Theater theater = theaterRepository.findById(theaterCode).orElseThrow(EntityNotFoundException::new);
        ticketing.setTheater(theater);

        return ticketingRepository.save(ticketing);
    }




    //상영관 정보 받아서 저장하고

    //상영관
}
