package com.HiBro.service;

import com.HiBro.repository.TheaterRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class TheaterService {
	private final TheaterRepository theaterRepository;
}
