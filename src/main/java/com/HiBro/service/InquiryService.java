package com.HiBro.service;

import com.HiBro.dto.InquiryDTO;
import com.HiBro.entity.Inquiry;
import com.HiBro.entity.Member;
import com.HiBro.repository.InquiryRepository;
import com.HiBro.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class InquiryService{
    private final InquiryRepository inquiryRepository;
    private final MemberRepository memberRepository;
    public Inquiry saveInquiry(InquiryDTO inquiryDTO, String id){
        Inquiry inquiry = Inquiry.createInquiry(inquiryDTO);
        Member member = memberRepository.findById(id);

        inquiry.setMember(member);
        return inquiryRepository.save(inquiry);
    }
    public void deleteInquiry(InquiryDTO inquiryDTO){
        Inquiry inquiry = inquiryRepository.findById(inquiryDTO.getCode())
                .orElseThrow(EntityNotFoundException::new);
        inquiryRepository.delete(inquiry);
    }
    public Page<Inquiry> getInquiryAll(Pageable pageable){
        return inquiryRepository.findAll(pageable);
    }
    public Inquiry getInqury(Long code){
        return inquiryRepository.findById(code).orElseThrow(EntityNotFoundException::new);
    }
    public void deleteMemberInquiry(Long memberCode){
        List<Inquiry> inquirieList = inquiryRepository.findByMember(memberCode);
        for(Inquiry i : inquirieList){
            inquiryRepository.delete(i);
        }
    }
}
