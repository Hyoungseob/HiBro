package com.HiBro.repository;


import com.HiBro.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member,Long>{
    Member findById(String id);
}
