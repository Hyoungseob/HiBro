package com.HiBro.repository;

import com.HiBro.entity.Seat;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SeatRepository extends JpaRepository<Seat, Long> {
	Seat findByCode(Long code);

	List<Seat> findByScreenDateCode(Long screenDateCode);
}
