package com.youngjo.ssg.domain.user.domain;

import com.youngjo.ssg.global.common.BaseEntity;
import com.youngjo.ssg.global.common.IdGenTable;
import com.youngjo.ssg.global.common.SeqTable;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@IdGenTable
public class ReviewImg extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = SeqTable.name)
    @Column(name = "review_img_id")
    private Long id;

    private String name;
    private String originPath;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "review_id")
    private Review review;

    @Builder
    public ReviewImg(String name, String originPath, Review review) {
        this.name = name;
        this.originPath = originPath;
        this.review = review;
    }
}



