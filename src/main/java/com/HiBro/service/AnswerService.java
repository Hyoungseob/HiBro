package com.HiBro.service;

import com.HiBro.dto.AnswerDTO;
import com.HiBro.entity.Answer;
import com.HiBro.entity.Inquiry;
import com.HiBro.entity.Member;
import com.HiBro.repository.AnswerRepository;
import com.HiBro.repository.InquiryRepository;
import com.HiBro.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class AnswerService{
    private final AnswerRepository answerRepository;
    private final InquiryRepository inquiryRepository;
    private final MemberRepository memberRepository;

    public Answer getAnswer(Long inquiryCode){
        return answerRepository.findByInquiryCode(inquiryCode);
    }
    public void saveAnswer(AnswerDTO answerDTO,Long inquiryCode){
        Answer answer = Answer.createAnswer(answerDTO);
        Inquiry inquiry = inquiryRepository.findById(inquiryCode).get();
        Member member = memberRepository.findById(inquiry.getMember().getCode()).get();
        answer.setInquiry(inquiry);
        answer.setMember(member);;
        answerRepository.save(answer);
    }
}
