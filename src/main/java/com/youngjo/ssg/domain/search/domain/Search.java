package com.youngjo.ssg.domain.search.domain;

import com.youngjo.ssg.global.common.BaseEntity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/***
 * 검색기록 보관용 엔티티
 */
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Search extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "search_id")
    private Long id;
    private String query;

    // == Auto Count ==
    private Integer frequency;

    @Builder
    public Search(String query) {
        this.query = query;
        this.frequency = 0;
    }

    public Search plusFrequency() {
        this.frequency++;
        return this;
    }
}
