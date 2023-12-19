package com.HiBro.repository;

import com.HiBro.entity.Screen;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ScreenRepository extends JpaRepository<Screen, Long> {
	Screen findByCode(Long screenCode);

	List<Screen> findByTheaterCode(Long theaterCode);

	@Query("SELECT S FROM Screen S WHERE S.screenLocation LIKE %:screenLocation% ORDER BY S.code DESC")
	List<Screen> findByScreenLocation(@Param("screenLocation") String screenLocation);

	@Query("SELECT S FROM Screen S WHERE S.screenType LIKE %:screenType% ORDER BY S.code DESC")
	List<Screen> findByScreenType(@Param("screenType") String screenType);
}
