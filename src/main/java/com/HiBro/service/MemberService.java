package com.HiBro.service;

import com.HiBro.constant.Role;
import com.HiBro.dto.MemberDTO;
import com.HiBro.dto.MemberSearchDTO;
import com.HiBro.entity.Member;
import com.HiBro.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class MemberService implements UserDetailsService  {
    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;
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
    public void updateMember(Long memberCode,Integer point){
        Member findMember = memberRepository.findById(memberCode)
                .orElseThrow(EntityNotFoundException::new);
        findMember.setPoint(point);
    }
    public void deleteMember(Long memberCode){
        Member member = memberRepository.findById(memberCode)
                        .orElseThrow(EntityNotFoundException::new);
        memberRepository.delete(member);
    }
    public Page<Member> getMemberAll(MemberSearchDTO memberSearchDTO, Pageable pageable){
        if(memberSearchDTO.getSearchId() != null){
            List<Member> member = memberRepository.findByIdContaining(memberSearchDTO.getSearchId());
            return new PageImpl<>(member,pageable, memberRepository.countBy());
        }
        return memberRepository.findAll(pageable);
    }
    public Page<Member> getAdminAll(Pageable pageable){
        List<Member> memberList=memberRepository.findByRole(Role.ADMIN);
        return new PageImpl<>(memberList,pageable, memberRepository.countByRole(Role.ADMIN));
    }
    public Member getMember(Long memberCode){
        return memberRepository.findById(memberCode).get();
    }
    public Member getMember(String memberId){
        return memberRepository.findById(memberId);
    }
    public void updateMember(MemberDTO memberDTO){
        if(memberDTO.getCode() != null){
            Member member = memberRepository.findById(memberDTO.getCode()).get();
            System.out.println(member+"멤버");
            member.setName(memberDTO.getName());
            member.setEmail(memberDTO.getEmail());
            member.setPassword(passwordEncoder.encode(memberDTO.getPassword()));
        }
    }

    @Override
    public UserDetails loadUserByUsername(String id) throws UsernameNotFoundException {
        Member member = memberRepository.findById(id);

        if (member == null) {
            throw new UsernameNotFoundException(id);
        }

        return User.builder()
                .username(member.getId())
                .password(member.getPassword())
                .roles(member.getRole().toString())
                .build();
    }
}
