package com.HiBro.repository;

import com.HiBro.entity.Inquiry;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface InquiryRepository extends JpaRepository<Inquiry,Long>{
    List<Inquiry> findByMemberCode(Long memberCode);
}
