package com.HiBro.repository;

import com.HiBro.entity.ScreenDate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ScreenDateRepository extends JpaRepository<ScreenDate, Long> {
	ScreenDate findByCode(Long code);

	List<ScreenDate> findByScreenCode(Long screenCode);

	Page<ScreenDate> findByScreenCode(Long screenCode, Pageable pageable);

	List<ScreenDate> findByScreenCodeAndMovieCode(Long screenCode, Long MovieCode);
}
