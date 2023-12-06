package com.HiBro.repository;

import com.HiBro.entity.TheaterDate;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TheaterDateRepository extends JpaRepository<TheaterDate, Long> {
	@Query("SELECT t FROM TheaterDate t WHERE t.theater.theaterCode = :theaterCode")
	List<TheaterDate> findTheaterDateByTheaterCode(@Param("theaterCode") Long theaterCode);
}
