package com.HiBro.repository;

import com.HiBro.entity.Ticketing;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TicketingRepository extends JpaRepository<Ticketing, Long> {

    List<Ticketing> findByMemberCodeOrderByCodeAsc(Long MemberCode);

}
