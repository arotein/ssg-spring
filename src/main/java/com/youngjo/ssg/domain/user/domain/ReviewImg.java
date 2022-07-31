package com.youngjo.ssg.domain.user.domain;

import com.youngjo.ssg.global.common.BaseEntity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class ReviewImg extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "review_img_id")
    private Long id;

    private String saveName;
    private String originName;
    private String path;
    private String img;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "review_id")
    private Review review;

    @Builder
    public ReviewImg(String saveName, String originName, String path, String img, Review review) {
        this.saveName = saveName;
        this.originName = originName;
        this.path = path;
        this.img = img;
        this.review = review;
    }
}



