package com.HiBro.entity;

import com.HiBro.dto.AnswerDTO;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.modelmapper.ModelMapper;

import javax.persistence.*;

@Entity
@Table(name = "Answer")
@Getter @Setter
public class Answer{
    @Id
    @Column(name = "answer_code")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long code;
    @Column(nullable = false)
    private String content;

    @ManyToOne
    @JoinColumn(name = "member_code")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Member member;

    @OneToOne
    @JoinColumn(name = "inquiry_code")
    private Inquiry inquiry;

    private static ModelMapper modelMapper = new ModelMapper();
    public static Answer createAnswer(AnswerDTO answerDTO){
        Answer answer = new Answer();
        answer.setContent(answerDTO.getContent());
        return answer;
    }
    public static AnswerDTO of(Answer answer){
        if(answer !=null){
            return modelMapper.map(answer, AnswerDTO.class);
        }
        return new AnswerDTO();
    }
}
