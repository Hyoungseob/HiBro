package com.HiBro.repository;

import com.HiBro.constant.AgeLimit;
import com.HiBro.constant.Role;
import com.HiBro.entity.*;
import com.HiBro.entity.Member;
import com.HiBro.entity.Movie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import javax.transaction.Transactional;
import java.time.LocalDateTime;

@SpringBootTest
@Transactional
@TestPropertySource(locations = "classpath:application-test.properties")
class TicketingRepositoryTest {
    @Autowired
    MemberRepository memberRepository;

    @Autowired
    MovieRepository movieRepository;

    @Autowired
    TheaterRepository theaterRepository;

    @Autowired
    TicketingRepository ticketingRepository;


    Member 테스트용_멤버_데이터() {

        Member member = new Member();

        member.setEmail("dfads");
        member.setId("sfdsdf");
        member.setName("safdf");
        member.setPoint(100);
        member.setRole(Role.USER);
        member.setPassword("sdfsf");
        member.setRegDate(LocalDateTime.now());

        return memberRepository.save(member);
    }

    Movie 테스트용_영화_데이터(){
        Movie movie = new Movie();

        movie.setMovieTitle("영화다");
        movie.setGenre("개그");
        movie.setSummary("몰라");
        movie.setDirector("하정수");
        movie.setActor("조범준");
        movie.setAgeLimit(AgeLimit.ADULT);

        return movieRepository.save(movie);

    }

   /* Theater 테스트용_상영관_데이터() {
        Theater theater = new Theater();

        theater.setTheaterLocation("울산 삼산");
        theater.setTheaterImg("사진");
        theater.setTheaterType("조범준의 집");

        return theaterRepository.save(theater);
    }*/

    /*Screen 테스트용_상영관_데이터(){
        Screen screen = new Screen();

        screen.setTheaterLocation("울산 삼산");
        screen.setTheaterImg("사진");
        screen.setTheaterType("조범준의 집");

        return theaterRepository.save(screen);
>>>>>>>>> Temporary merge branch 2
    }

    @Test
    void 예매_생성및_조회기능_테스트(){

        Ticketing ticketing = new Ticketing(테스트용_멤버_데이터());

        ticketing.setMovie(테스트용_영화_데이터());
        ticketing.setTheater(테스트용_상영관_데이터());

        ticketingRepository.save(ticketing);

        ticketingRepository.findByMemberCodeOrderByCodeAsc(ticketing.getMember().getCode());
<<<<<<<<< Temporary merge branch 1
    }
=========
    }*/


}