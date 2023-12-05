package com.HiBro.entity;

import com.HiBro.constant.InquiryStatus;
import com.HiBro.dto.InquiryDTO;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name="Inquiry")
@Getter @Setter
public class Inquiry{
    @Id
    @Column(name = "inquiry_code")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long code;
    @Column(nullable = false)
    private String title;
    @Column(nullable = false)
    private String content;
    private LocalDateTime regDate;
    private InquiryStatus inquiryStatus;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_code")
    private Member member;

    public static Inquiry createInquiry(InquiryDTO inquiryDTO){
        Inquiry inquiry = new Inquiry();
        inquiry.setTitle(inquiryDTO.getTitle());
        inquiry.setContent(inquiryDTO.getContent());
        inquiry.setRegDate(LocalDateTime.now());
        inquiry.setInquiryStatus(InquiryStatus.ING);
        return inquiry;
    }
}
