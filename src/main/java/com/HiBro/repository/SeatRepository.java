package com.HiBro.repository;

import com.HiBro.entity.Seat;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SeatRepository extends JpaRepository<Seat, Long> {
	Seat findByCode(Long code);

	@Query("SELECT S FROM Seat S WHERE S.screen.code = :screenCode")
	List<Seat> findSeatByScreenCode(@Param("screenCode") Long screenCode);
}
