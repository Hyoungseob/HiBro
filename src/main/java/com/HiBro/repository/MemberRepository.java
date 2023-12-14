package com.HiBro.repository;


import com.HiBro.constant.Role;
import com.HiBro.entity.Member;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MemberRepository extends JpaRepository<Member, Long> {
	Member findById(String id);
	List<Member> findByIdContaining(String id);
	List<Member> findByRole(Role role);
}
