package com.HiBro.repository;

import com.HiBro.entity.ScreenDate;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ScreenDateRepository extends JpaRepository<ScreenDate, Long> {
	ScreenDate findByCode(Long code);

	List<ScreenDate> findByScreenCode(Long screenCode);
}
