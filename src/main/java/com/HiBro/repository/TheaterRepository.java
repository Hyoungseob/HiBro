package com.HiBro.repository;

import com.HiBro.entity.Screen;
import com.HiBro.entity.Theater;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface TheaterRepository extends JpaRepository<Theater, Long> {
	Theater findByCode(Long code);

	Page<Theater> findAll(Specification<Theater> theaterSpecification, Pageable pageable);
}
