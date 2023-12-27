package com.HiBro.repository;

import com.HiBro.entity.Answer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AnswerRepository extends JpaRepository<Answer,Long>{
    Answer findByInquiryCode(Long inquiryCode);
}
