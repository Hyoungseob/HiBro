package com.HiBro.repository;

import com.HiBro.constant.Location;
import com.HiBro.constant.ScreenType;
import com.HiBro.entity.Screen;
import org.springframework.data.domain.*;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ScreenRepository extends JpaRepository<Screen, Long> {
	Screen findByCode(Long screenCode);

	List<Screen> findByTheaterCode(Long theaterCode);

	Page<Screen> findByTheaterCode(Long theaterCode, Pageable pageable);

	Page<Screen> findByScreenType(ScreenType screenType, Pageable pageable);

	@Query("SELECT s FROM Screen s JOIN s.theater t WHERE t.location = :location")
	Page<Screen> findByTheaterLocation(@Param("location") Location location, Pageable pageable);

	@Query("SELECT s FROM Screen s JOIN s.theater t WHERE s.screenType = :screenType AND t.location = :location")
	Page<Screen> findByScreenTypeAndTheaterLocation(@Param("screenType") ScreenType screenType, @Param("location") Location location, Pageable pageable);
}
