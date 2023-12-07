package com.HiBro.repository;

import com.HiBro.entity.TheaterDate;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TheaterDateRepository extends JpaRepository<TheaterDate, Long> {
	TheaterDate findByCode(Long code);

	@Query("SELECT t FROM TheaterDate t WHERE t.theater.code = :theaterCode")
	List<TheaterDate> findTheaterDateByTheaterCode(@Param("theaterCode") Long theaterCode);
}
