package com.HiBro.repository;

import com.HiBro.entity.Seat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SeatRepository extends JpaRepository<Seat, Long> {
	@Query("SELECT s FROM Seat s WHERE s.theater.theaterCode = :theaterCode")
	List<Seat> findByTheaterCode(@Param("theaterCode") Long theaterCode);
}
