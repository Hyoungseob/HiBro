package com.HiBro.repository;

import com.HiBro.entity.Theater;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TheaterRepository extends JpaRepository<Theater, Long> {
	@Query("SELECT T FROM Theater T WHERE T.theaterLocation LIKE %:theaterLocation% ORDER BY T.theaterCode DESC")
	List<Theater> findByTheaterLocation(@Param("theaterLocation") String theaterLocation);

	@Query("SELECT T FROM Theater T WHERE T.theaterType LIKE %:theaterType% ORDER BY T.theaterCode DESC")
	List<Theater> findByTheaterType(@Param("theaterType") String theaterType);
}
