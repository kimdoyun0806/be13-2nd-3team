package com.beyond3.yyGang.answer.domain;

import com.beyond3.yyGang.EntityDate;
import com.beyond3.yyGang.q_board.QuestionBoard;
import com.beyond3.yyGang.user.domain.User;
import jakarta.persistence.*;
import lombok.*;
import org.apache.commons.lang3.StringUtils;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "answer")
public class Answer extends EntityDate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long answerId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "qboard_id")
    private QuestionBoard qboard;

    private String answerContent;

    public void update(String answerContent) {

        // 내용에 변경이 있는 경우에만 update
        if(StringUtils.isNotBlank(answerContent)){
            this.answerContent = answerContent;
        }
    }
}