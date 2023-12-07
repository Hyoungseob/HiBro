package com.HiBro.service;

import com.HiBro.constant.Role;
import com.HiBro.dto.TheaterDTO;
import com.HiBro.entity.*;
import com.HiBro.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class TheaterService {
	private final TheaterRepository theaterRepository;
	private final MemberRepository memberRepository;

	public Theater saveTheater(TheaterDTO theaterDTO, String id) {
		Member member = memberRepository.findById(id);
		if (member.getRole() == Role.ADMIN) {
			Theater theater = Theater.createTheater(theaterDTO);
			return theaterRepository.save(theater);
		}
		return null;
	}

	public void deleteTheater(TheaterDTO theaterDTO, String id) {
		Member member = memberRepository.findById(id);
		if (member.getRole() == Role.ADMIN) {
			Theater theater = theaterRepository.findByCode(theaterDTO.getCode());
			theaterRepository.delete(theater);
		}
	}
}
