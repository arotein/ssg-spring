package com.youngjo.ssg.domain.product.service;

import com.youngjo.ssg.domain.product.domain.CategoryL4;
import com.youngjo.ssg.domain.product.domain.ProductBoard;
import com.youngjo.ssg.domain.product.domain.ProductBoardLike;
import com.youngjo.ssg.domain.product.dto.request.BoardSortFilterReqDto;
import com.youngjo.ssg.domain.product.dto.request.AddPdtBoardReqDto;
import com.youngjo.ssg.domain.product.dto.response.BoardListResDto;
import com.youngjo.ssg.domain.product.dto.response.BoardResDto;
import com.youngjo.ssg.domain.product.dto.response.PdtBoardDetailResDto;
import com.youngjo.ssg.domain.product.repository.CategoryL4Repository;
import com.youngjo.ssg.domain.product.repository.ProductRepository;
import com.youngjo.ssg.domain.user.repository.UserRepository;
import com.youngjo.ssg.global.enumeration.SalesSite;
import com.youngjo.ssg.global.security.bean.ClientInfoLoader;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ClientInfoLoader clientInfoLoader;
    private final UserRepository userRepository;
    private final ProductRepository productRepository;
    private final CategoryL4Repository categoryL4Repository;


    @Override
    public void addPdtBoard(AddPdtBoardReqDto addPdtBoardReqDto) {
        CategoryL4 ctgL4 = categoryL4Repository.findById(addPdtBoardReqDto.getCtgL4Id()).plusPdtQty();
        ctgL4.getCategoryL3().plusPdtQty()
                .getCategoryL2().plusPdtQty()
                .getCategoryL1().plusPdtQty();

        productRepository.save(
                ProductBoard.builder()
                        .title(addPdtBoardReqDto.getTitle())
                        .brand(addPdtBoardReqDto.getBrand())
                        .salesSite(SalesSite.CONSTRUCT.findInstance(addPdtBoardReqDto.getSalesSite()))
                        .isEachShippingFee(addPdtBoardReqDto.getIsEachShippingFee())
                        .isPremium(addPdtBoardReqDto.getIsPremium())
                        .isCrossBorderShipping(addPdtBoardReqDto.getIsCrossBorderShipping())
                        .isOnlineOnly(addPdtBoardReqDto.getIsOnlineOnly())
                        .shippingFee(addPdtBoardReqDto.getShippingFee())
                        .shippingFreeOver(addPdtBoardReqDto.getShippingFreeOver())
                        .availableDeliveryJeju(addPdtBoardReqDto.getAvailableDeliveryJeju())
                        .availableDeliveryIsland(addPdtBoardReqDto.getAvailableDeliveryIsland())
                        .shippingFeeJeju(addPdtBoardReqDto.getShippingFeeJeju())
                        .shippingFeeIsland(addPdtBoardReqDto.getShippingFeeIsland())
                        .courierCompany(addPdtBoardReqDto.getCourierCompany())
                        .pdtName(addPdtBoardReqDto.getPdtName())
                        .returnAddress(addPdtBoardReqDto.getReturnAddress())
                        .exchangeShippingFee(addPdtBoardReqDto.getExchangeShippingFee())
                        .returnShippingFee(addPdtBoardReqDto.getReturnShippingFee())
                        .premiumExchangeShippingFee(addPdtBoardReqDto.getPremiumExchangeShippingFee())
                        .premiumReturnShippingFee(addPdtBoardReqDto.getPremiumReturnShippingFee())
                        .consignmentSellerInfo(addPdtBoardReqDto.getConsignmentSellerInfo())
                        .productRequiredInfoList(addPdtBoardReqDto.getRequiredInfoList())

                        .build()

                        .linkToProductThumbImgList(addPdtBoardReqDto.getThumbImgList())
                        .linkToProductDetailImgList(addPdtBoardReqDto.getDetailImgList())
                        .linkToProductList(addPdtBoardReqDto.getMainProductList())
                        .linkToCategoryL4(ctgL4)
                        .linkToReturnAddress(addPdtBoardReqDto.getReturnAddress())
                        .linkToProductRequiredInfoList(addPdtBoardReqDto.getRequiredInfoList())
                        .linkToConsignmentSellerInfo(addPdtBoardReqDto.getConsignmentSellerInfo())
                        .linkToTagList(addPdtBoardReqDto.getTag())
        );
    }

    // 상품 상세보기
    @Override
    public PdtBoardDetailResDto getBoardById(Long boardId) {
        return new PdtBoardDetailResDto(productRepository.findBoardById(boardId))
                .boardLike(clientInfoLoader.getUserId() == null ? false : productRepository.findBoardLikeByBoardIdAndUserId(boardId, clientInfoLoader.getUserId()).getValue());
    }

    @Transactional(readOnly = true)
    @Override
    public BoardListResDto getBoardListByL2Id(Long id, BoardSortFilterReqDto queryDto) {
        Long userId = clientInfoLoader.getUserId();
        List<ProductBoard> boardList = productRepository.findBoardListByL2Id(id,
                queryDto.getOffset(),
                queryDto.getLimit(),
                queryDto.getSort(),
                queryDto.getMinPrice(),
                queryDto.getMaxPrice());
        Map<Long, Boolean> likeMap;
        if (userId != null) {
            likeMap = productRepository.findBoardLikeMapByBoardIdAndUserId(
                    boardList.stream().map(ProductBoard::getId).collect(Collectors.toList()),
                    userId);
        } else {
            likeMap = new HashMap<>();
        }

        return boardList.size() > 0
                ? new BoardListResDto(
                boardList.get(0).getCategoryL4().getCategoryL3().getCategoryL2().getPdtQty(),
                boardList.stream()
                        .map(board -> new BoardResDto(board, likeMap.getOrDefault(board.getId(), false)))
                        .collect(Collectors.toList()))
                : new BoardListResDto(0, Arrays.asList());
    }

    @Transactional(readOnly = true)
    @Override
    public BoardListResDto getBoardListByL3Id(Long id, BoardSortFilterReqDto queryDto) {
        Long userId = clientInfoLoader.getUserId();
        List<ProductBoard> boardList = productRepository.findBoardListByL3Id(id,
                queryDto.getOffset(),
                queryDto.getLimit(),
                queryDto.getSort(),
                queryDto.getMinPrice(),
                queryDto.getMaxPrice());
        Map<Long, Boolean> likeMap;
        if (userId != null) {
            likeMap = productRepository.findBoardLikeMapByBoardIdAndUserId(
                    boardList.stream().map(ProductBoard::getId).collect(Collectors.toList()),
                    userId);
        } else {
            likeMap = new HashMap<>();
        }

        return boardList.size() > 0
                ? new BoardListResDto(
                boardList.get(0).getCategoryL4().getCategoryL3().getPdtQty(),
                boardList.stream()
                        .map(board -> new BoardResDto(board, likeMap.getOrDefault(board.getId(), false)))
                        .collect(Collectors.toList()))
                : new BoardListResDto(0, Arrays.asList());
    }

    @Transactional(readOnly = true)
    @Override
    public BoardListResDto getBoardListByL4Id(Long id, BoardSortFilterReqDto queryDto) {
        Long userId = clientInfoLoader.getUserId();
        List<ProductBoard> boardList = productRepository.findBoardListByL4Id(id,
                queryDto.getOffset(),
                queryDto.getLimit(),
                queryDto.getSort(),
                queryDto.getMinPrice(),
                queryDto.getMaxPrice());
        Map<Long, Boolean> likeMap;
        if (userId != null) {
            likeMap = productRepository.findBoardLikeMapByBoardIdAndUserId(
                    boardList.stream().map(ProductBoard::getId).collect(Collectors.toList()),
                    userId);
        } else {
            likeMap = new HashMap<>();
        }

        return boardList.size() > 0
                ? new BoardListResDto(
                boardList.get(0).getCategoryL4().getPdtQty(),
                boardList.stream()
                        .map(board -> new BoardResDto(board, likeMap.getOrDefault(board.getId(), false)))
                        .collect(Collectors.toList()))
                : new BoardListResDto(0, Arrays.asList());
    }

    @Override
    public void pressBoardLike(Long boardId) {
        ProductBoardLike boardLike = productRepository.findBoardLikeByBoardIdAndUserId(boardId, clientInfoLoader.getUserId());
        if (boardLike != null) {
            boardLike.pressLike();
        } else {
            productRepository.save(ProductBoardLike.builder().value(true).build()
                    .linkToProductBoard(productRepository.findBoardById(boardId))
                    .linkToUser(userRepository.findUserById(clientInfoLoader.getUserId())));
        }
    }
}
