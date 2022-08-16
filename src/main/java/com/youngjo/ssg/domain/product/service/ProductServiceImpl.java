package com.youngjo.ssg.domain.product.service;

import com.youngjo.ssg.domain.product.domain.CategoryL4;
import com.youngjo.ssg.domain.product.domain.ProductBoard;
import com.youngjo.ssg.domain.product.domain.ProductBoardLike;
import com.youngjo.ssg.domain.product.dto.request.PdtBoardAddReqDto;
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

import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
    public void addPdtBoard(PdtBoardAddReqDto pdtBoardAddReqDto) {
        CategoryL4 ctgL4 = categoryL4Repository.findById(pdtBoardAddReqDto.getCtgL4Id()).plusPdtQty();
        ctgL4.getCategoryL3().plusPdtQty()
                .getCategoryL2().plusPdtQty()
                .getCategoryL1().plusPdtQty();

        productRepository.save(
                ProductBoard.builder()
                        .title(pdtBoardAddReqDto.getTitle())
                        .brand(pdtBoardAddReqDto.getBrand())
                        .salesSite(SalesSite.CONSTRUCT.findInstance(pdtBoardAddReqDto.getSalesSite()))
                        .isEachShippingFee(pdtBoardAddReqDto.getIsEachShippingFee())
                        .isPremium(pdtBoardAddReqDto.getIsPremium())
                        .isCrossBorderShipping(pdtBoardAddReqDto.getIsCrossBorderShipping())
                        .isOnlineOnly(pdtBoardAddReqDto.getIsOnlineOnly())
                        .shippingFee(pdtBoardAddReqDto.getShippingFee())
                        .shippingFreeOver(pdtBoardAddReqDto.getShippingFreeOver())
                        .availableDeliveryJeju(pdtBoardAddReqDto.getAvailableDeliveryJeju())
                        .availableDeliveryIsland(pdtBoardAddReqDto.getAvailableDeliveryIsland())
                        .shippingFeeJeju(pdtBoardAddReqDto.getShippingFeeJeju())
                        .shippingFeeIsland(pdtBoardAddReqDto.getShippingFeeIsland())
                        .courierCompany(pdtBoardAddReqDto.getCourierCompany())
                        .pdtName(pdtBoardAddReqDto.getPdtName())
                        .returnAddress(pdtBoardAddReqDto.getReturnAddress())
                        .exchangeShippingFee(pdtBoardAddReqDto.getExchangeShippingFee())
                        .returnShippingFee(pdtBoardAddReqDto.getReturnShippingFee())
                        .premiumExchangeShippingFee(pdtBoardAddReqDto.getPremiumExchangeShippingFee())
                        .premiumReturnShippingFee(pdtBoardAddReqDto.getPremiumReturnShippingFee())
                        .consignmentSellerInfo(pdtBoardAddReqDto.getConsignmentSellerInfo())
                        .productRequiredInfoList(pdtBoardAddReqDto.getRequiredInfoList())

                        .build()

                        .linkToProductThumbImgList(pdtBoardAddReqDto.getThumbImgList())
                        .linkToProductDetailImgList(pdtBoardAddReqDto.getDetailImgList())
                        .linkToProductList(pdtBoardAddReqDto.getMainProductList())
                        .linkToCategoryL4(ctgL4)
                        .linkToReturnAddress(pdtBoardAddReqDto.getReturnAddress())
                        .linkToProductRequiredInfo(pdtBoardAddReqDto.getRequiredInfoList())
                        .linkToConsignmentSellerInfo(pdtBoardAddReqDto.getConsignmentSellerInfo())
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
    public BoardListResDto getBoardListByL2Id(Long id, Integer offset, Integer limit) {
        Long userId = clientInfoLoader.getUserId();
        List<ProductBoard> boardList = productRepository.findBoardListByL2Id(id, offset, limit);
        Map<Long, Boolean> likeMap;
        if (userId != null) {
            likeMap = productRepository.findBoardLikeMapByBoardIdAndUserId(
                    boardList.stream().map(ProductBoard::getId).collect(Collectors.toList()),
                    userId);
        } else {
            likeMap = new HashMap<>();
        }

        return new BoardListResDto(
                boardList.get(0).getCategoryL4().getCategoryL3().getCategoryL2().getPdtQty(),
                boardList.stream()
                        .map(board -> new BoardResDto(board, likeMap.getOrDefault(board.getId(), false)))
                        .collect(Collectors.toList()));
    }

    @Transactional(readOnly = true)
    @Override
    public BoardListResDto getBoardListByL3Id(Long id, Integer offset, Integer limit) {
        Long userId = clientInfoLoader.getUserId();
        List<ProductBoard> boardList = productRepository.findBoardListByL3Id(id, offset, limit);
        Map<Long, Boolean> likeMap;
        if (userId != null) {
            likeMap = productRepository.findBoardLikeMapByBoardIdAndUserId(
                    boardList.stream().map(ProductBoard::getId).collect(Collectors.toList()),
                    userId);
        } else {
            likeMap = new HashMap<>();
        }

        return new BoardListResDto(
                boardList.get(0).getCategoryL4().getCategoryL3().getPdtQty(),
                boardList.stream()
                        .map(board -> new BoardResDto(board, likeMap.getOrDefault(board.getId(), false)))
                        .collect(Collectors.toList()));
    }

    @Transactional(readOnly = true)
    @Override
    public BoardListResDto getBoardListByL4Id(Long id, Integer offset, Integer limit) {
        Long userId = clientInfoLoader.getUserId();
        List<ProductBoard> boardList = productRepository.findBoardListByL4Id(id, offset, limit);
        Map<Long, Boolean> likeMap;
        if (userId != null) {
            likeMap = productRepository.findBoardLikeMapByBoardIdAndUserId(
                    boardList.stream().map(ProductBoard::getId).collect(Collectors.toList()),
                    userId);
        } else {
            likeMap = new HashMap<>();
        }

        return new BoardListResDto(
                boardList.get(0).getCategoryL4().getPdtQty(),
                boardList.stream()
                        .map(board -> new BoardResDto(board, likeMap.getOrDefault(board.getId(), false)))
                        .collect(Collectors.toList()));
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
