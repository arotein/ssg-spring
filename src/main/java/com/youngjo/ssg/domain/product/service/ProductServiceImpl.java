package com.youngjo.ssg.domain.product.service;

import com.youngjo.ssg.domain.product.domain.*;
import com.youngjo.ssg.domain.product.dto.request.AddPdtBoardReqDto;
import com.youngjo.ssg.domain.product.dto.request.BoardSortFilterReqDto;
import com.youngjo.ssg.domain.product.dto.response.BoardListResDto;
import com.youngjo.ssg.domain.product.dto.response.BoardResDto;
import com.youngjo.ssg.domain.product.dto.response.PdtBoardDetailResDto;
import com.youngjo.ssg.domain.product.dto.response.PdtOption2ResDto;
import com.youngjo.ssg.domain.product.repository.CategoryL4Repository;
import com.youngjo.ssg.domain.product.repository.ProductRepository;
import com.youngjo.ssg.domain.user.domain.Address;
import com.youngjo.ssg.domain.user.repository.UserRepository;
import com.youngjo.ssg.global.enumeration.SalesSite;
import com.youngjo.ssg.global.security.bean.ClientInfoLoader;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
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
    public Boolean addPdtBoard(AddPdtBoardReqDto addPdtBoardReqDto) {
        Address address = Address.builder()
                .city(addPdtBoardReqDto.getReturnAddress().getCity())
                .street(addPdtBoardReqDto.getReturnAddress().getStreet())
                .detail(addPdtBoardReqDto.getReturnAddress().getDetail())
                .postalCode(addPdtBoardReqDto.getReturnAddress().getPostalCode())
                .build()
                .returnThis();

        List<Tag> tagList = addPdtBoardReqDto.getTagList().stream()
                .map(tag -> Tag.builder().keyword(tag.getKeyword()).build())
                .collect(Collectors.toList());

        List<MainProduct> pdtList = addPdtBoardReqDto.getMainProductList().stream()
                .map(pdt -> MainProduct.builder()
                        .modelCode(pdt.getModelCode())
                        .optionValue1(pdt.getOptionValue1())
                        .optionValue2(pdt.getOptionValue2())
                        .price(pdt.getPrice())
                        .stock(pdt.getStock())
                        .build())
                .collect(Collectors.toList());

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
                        .optionName1(addPdtBoardReqDto.getOptionName1())
                        .optionName2(addPdtBoardReqDto.getOptionName2())
                        .exchangeShippingFee(addPdtBoardReqDto.getExchangeShippingFee())
                        .returnShippingFee(addPdtBoardReqDto.getReturnShippingFee())
                        .premiumExchangeShippingFee(addPdtBoardReqDto.getPremiumExchangeShippingFee())
                        .premiumReturnShippingFee(addPdtBoardReqDto.getPremiumReturnShippingFee())

                        .build()

                        .linkToProductThumbImgList(addPdtBoardReqDto.getThumbImgList())
                        .linkToProductDetailImgList(addPdtBoardReqDto.getDetailImgList())
                        .linkToMainProductList(pdtList)
                        .linkToCategoryL4(ctgL4)
                        .linkToReturnAddress(address)
                        .linkToProductRequiredInfoList(addPdtBoardReqDto.getRequiredInfoList())
                        .linkToConsignmentSellerInfo(addPdtBoardReqDto.getConsignmentSellerInfo())
                        .linkToTagList(tagList)

                        .devCodeAddTotalReviewAndScore(addPdtBoardReqDto.getTotalReviewQty(), addPdtBoardReqDto.getTotalScore())
        );
        return true;
    }

    // 상품 상세보기
    @Transactional(readOnly = true)
    @Override
    public PdtBoardDetailResDto getBoardById(Long boardId) {
        ProductBoard board = productRepository.findBoardById(boardId);
        return new PdtBoardDetailResDto(board)
                .addOption1Set(board.getMainProductList().stream().map(MainProduct::getOptionValue1).collect(Collectors.toSet()))
                .boardLike(clientInfoLoader.getUserId() == null
                        ? false
                        : productRepository.findBoardLikeByBoardIdAndUserId(boardId, clientInfoLoader.getUserId()).getValue());
    }

    @Transactional(readOnly = true)
    @Override
    public List<PdtOption2ResDto> getPdtOption2List(Long boardId, String opt1Value) {
        return productRepository.findAllMainProductByOption(boardId, opt1Value)
                .stream()
                .map(pdt -> new PdtOption2ResDto(pdt.getId(), pdt.getOptionValue2(), pdt.getPrice(), pdt.getStock()))
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    @Override
    public BoardListResDto getBoardListByL2Id(Long id, BoardSortFilterReqDto queryDto) {
        Long userId = clientInfoLoader.getUserId();
        List<ProductBoard> boardList = productRepository.findBoardListByL2Id(id,
                queryDto.getLimit() * (queryDto.getPage() - 1),
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

        // 총 검색 결과
        Long boardCount = productRepository.countAllBoardByCtgId(
                id, null, null,
                queryDto.getMinPrice(),
                queryDto.getMaxPrice());

        return boardList.size() > 0
                ? new BoardListResDto(
                boardCount,
                boardList.stream()
                        .map(board -> new BoardResDto(board, likeMap.getOrDefault(board.getId(), false)))
                        .collect(Collectors.toList()))
                : new BoardListResDto(0L, Arrays.asList());
    }

    @Transactional(readOnly = true)
    @Override
    public BoardListResDto getBoardListByL3Id(Long id, BoardSortFilterReqDto queryDto) {
        Long userId = clientInfoLoader.getUserId();
        List<ProductBoard> boardList = productRepository.findBoardListByL3Id(id,
                queryDto.getLimit() * (queryDto.getPage() - 1),
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

        // 총 검색 결과
        Long boardCount = productRepository.countAllBoardByCtgId(
                null, id, null,
                queryDto.getMinPrice(),
                queryDto.getMaxPrice());

        return boardList.size() > 0
                ? new BoardListResDto(
                boardCount,
                boardList.stream()
                        .map(board -> new BoardResDto(board, likeMap.getOrDefault(board.getId(), false)))
                        .collect(Collectors.toList()))
                : new BoardListResDto(0L, Arrays.asList());
    }

    @Transactional(readOnly = true)
    @Override
    public BoardListResDto getBoardListByL4Id(Long id, BoardSortFilterReqDto queryDto) {
        Long userId = clientInfoLoader.getUserId();
        List<ProductBoard> boardList = productRepository.findBoardListByL4Id(id,
                queryDto.getLimit() * (queryDto.getPage() - 1),
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

        // 총 검색 결과
        Long boardCount = productRepository.countAllBoardByCtgId(
                null, null, id,
                queryDto.getMinPrice(),
                queryDto.getMaxPrice());

        return boardList.size() > 0
                ? new BoardListResDto(
                boardCount,
                boardList.stream()
                        .map(board -> new BoardResDto(board, likeMap.getOrDefault(board.getId(), false)))
                        .collect(Collectors.toList()))
                : new BoardListResDto(0L, Arrays.asList());
    }

    @Override
    public Boolean pressBoardLike(Long boardId) {
        ProductBoardLike boardLike = productRepository.findBoardLikeByBoardIdAndUserId(boardId, clientInfoLoader.getUserId());
        if (boardLike != null) {
            boardLike.pressLike();
        } else {
            productRepository.save(ProductBoardLike.builder().value(true).build()
                    .linkToProductBoard(productRepository.findBoardById(boardId))
                    .linkToUser(userRepository.findUserById(clientInfoLoader.getUserId())));
        }
        return true;
    }
}
