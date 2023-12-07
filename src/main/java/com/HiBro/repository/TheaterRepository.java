package com.HiBro.repository;

import com.HiBro.entity.Theater;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TheaterRepository extends JpaRepository<Theater, Long> {

	Theater findByCode(Long theaterCode);

	@Query("SELECT T FROM Theater T WHERE T.theaterLocation LIKE %:theaterLocation% ORDER BY T.code DESC")
	List<Theater> findByTheaterLocation(@Param("theaterLocation") String theaterLocation);

	@Query("SELECT T FROM Theater T WHERE T.theaterType LIKE %:theaterType% ORDER BY T.code DESC")
	List<Theater> findByTheaterType(@Param("theaterType") String theaterType);
}
