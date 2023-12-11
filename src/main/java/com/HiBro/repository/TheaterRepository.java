package com.HiBro.repository;

import com.HiBro.entity.Theater;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TheaterRepository extends JpaRepository<Theater, Long> {
	Theater findByCode(Long code);
}
