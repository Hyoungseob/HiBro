package com.HiBro.service;

import com.HiBro.entity.Member;
import com.HiBro.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class MemberService{
    private final MemberRepository memberRepository;

    public Member saveMember(Member member){
        if(checkMember(member)){
            return memberRepository.save(member);
        }else {
            return null;
        }
    }

    private boolean checkMember(Member member){
        Member findMember = memberRepository.findById(member.getId());

        if(findMember != null){
            return false;
        }
        return true;
    }

    public void deleteMember(Member member){
        if(!checkMember(member))
            memberRepository.delete(member);
    }
}
