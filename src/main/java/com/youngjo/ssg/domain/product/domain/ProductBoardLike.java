package com.youngjo.ssg.domain.product.domain;

import com.youngjo.ssg.domain.user.domain.User;
import com.youngjo.ssg.global.common.BaseEntity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/***
 * value true -> 좋아요 누름
 * value false -> 좋아요 해제
 */
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class ProductBoardLike extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "board_like_id")
    private Long id;
    private Boolean value;

    @Builder
    public ProductBoardLike(Boolean value) {
        this.value = value;
    }

    // == Mapping ==
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_board_id")
    private ProductBoard productBoard;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    public ProductBoardLike pressLike() {
        this.value = true;
        return this;
    }

    public ProductBoardLike cancelLike() {
        this.value = false;
        return this;
    }

    public ProductBoardLike linkToProductBoard(ProductBoard productBoard) {
        this.productBoard = productBoard;
        productBoard.linkToPdtBoardLike(this);
        return this;
    }

    public ProductBoardLike linkToUser(User user) {
        this.user = user;
        user.linkToPdtBoardLike(this);
        return this;
    }
}
