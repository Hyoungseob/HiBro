package com.HiBro.repository;

import com.HiBro.constant.ScreenType;
import com.HiBro.entity.Screen;
import org.springframework.data.domain.*;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ScreenRepository extends JpaRepository<Screen, Long>, JpaSpecificationExecutor<Screen> {
	Screen findByCode(Long screenCode);

	List<Screen> findByTheaterCode(Long theaterCode);

	Page<Screen> findByTheaterCode(Long theaterCode, Pageable pageable);

	@Query("SELECT S FROM Screen S WHERE S.screenLocation LIKE %:screenLocation% ORDER BY S.code DESC")
	List<Screen> findByScreenLocation(@Param("screenLocation") String screenLocation);

	Page<Screen> findAll(Specification<Screen> screenSpecification, Pageable pageable);
}
