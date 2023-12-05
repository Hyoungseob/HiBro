package com.HiBro.repository;

import com.HiBro.entity.Seat;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SeatRepository extends JpaRepository<Seat, Long> {
	List<Seat> findByTheaterCode(Long theaterCode);
}
