package com.beyond3.yyGang.nutrientQuestion;

import com.beyond3.yyGang.EntityDate;
import com.beyond3.yyGang.nsupplement.NSupplement;
import com.beyond3.yyGang.user.domain.User;
import jakarta.persistence.*;
import lombok.Getter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Getter
@Table(name = "n_question")
public class NQuestion extends EntityDate {
    // 영양제 관련 문의사항

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "question_id")
    private Long questionId; // 문의 사항 ID

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "products_id")
    private NSupplement supplement;

    @Column(columnDefinition = "TEXT")
    private String qContent; // 질문 내용

//    @OneToOne(mappedBy = "question")
//    private NAnswer nAnswer;
}