package com.beyond3.yyGang.q_board;

import com.beyond3.yyGang.EntityDate;
import com.beyond3.yyGang.user.domain.User;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.*;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "question_board")
public class QuestionBoard extends EntityDate {
    // 약 질문 게시판

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "qboard_id")
    private Long qboardId;  // 질문글 ID

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user; // 글 작성자

    private String qboardTitle; // 글 제목

    @Column(columnDefinition = "TEXT")
    private String qboardContent; // 글 내용

    @Column(columnDefinition = "integer default 0", nullable = false)
    private int viewCount;  // 리뷰 수 -> 기본 값은 0, null 값은 허용 x

    @Builder
    public QuestionBoard(String qboardTitle, String qboardContent, User user) {
        this.qboardTitle = qboardTitle;
        this.qboardContent = qboardContent;
        this.user = user;
    }

    public void update(String qboardTitle, String qboardContent){

        // 제목에 대한 변경이 있는 경우-
        if(StringUtils.isNotBlank(qboardTitle)){
            this.qboardTitle = qboardTitle;
        }

        // 게시글 내용에 변경이 있는 경우-
        if(StringUtils.isNotBlank(qboardContent)){
            this.qboardContent = qboardContent;
        }
    }
}