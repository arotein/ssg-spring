package com.youngjo.ssg.domain.product.service;

import com.youngjo.ssg.domain.product.domain.CategoryL4;
import com.youngjo.ssg.domain.product.domain.ProductBoard;
import com.youngjo.ssg.domain.product.dto.request.PdtBoardAddReqDto;
import com.youngjo.ssg.domain.product.dto.response.BoardListResDto;
import com.youngjo.ssg.domain.product.dto.response.BoardResDto;
import com.youngjo.ssg.domain.product.repository.CategoryL4Repository;
import com.youngjo.ssg.domain.product.repository.ProductRepository;
import com.youngjo.ssg.global.enumeration.SalesSite;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
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

                        .build()

                        .linkToProductThumbImgList(pdtBoardAddReqDto.getThumbImgList())
                        .linkToProductDetailImgList(pdtBoardAddReqDto.getDetailImgList())
                        .linkToProductList(pdtBoardAddReqDto.getMainProductList())
                        .linkToCategoryL4(ctgL4)
        );
    }

    @Transactional(readOnly = true)
    @Override
    public BoardListResDto getBoardListByL2Id(Long id, Integer offset, Integer limit) {
        List<ProductBoard> boardList = productRepository.findBoardListByL2Id(id, offset, limit);
        return new BoardListResDto(
                boardList.get(0).getCategoryL4().getCategoryL3().getCategoryL2().getPdtQty(),
                boardList.stream()
                        .map(board -> new BoardResDto(board))
                        .collect(Collectors.toList()));
    }

    @Transactional(readOnly = true)
    @Override
    public BoardListResDto getBoardListByL3Id(Long id, Integer offset, Integer limit) {
        List<ProductBoard> boardList = productRepository.findBoardListByL3Id(id, offset, limit);
        return new BoardListResDto(
                boardList.get(0).getCategoryL4().getCategoryL3().getPdtQty(),
                boardList.stream()
                        .map(board -> new BoardResDto(board))
                        .collect(Collectors.toList()));
    }

    @Transactional(readOnly = true)
    @Override
    public BoardListResDto getBoardListByL4Id(Long id, Integer offset, Integer limit) {
        List<ProductBoard> boardList = productRepository.findBoardListByL4Id(id, offset, limit);
        return new BoardListResDto(
                boardList.get(0).getCategoryL4().getPdtQty(),
                boardList.stream()
                        .map(board -> new BoardResDto(board))
                        .collect(Collectors.toList()));
    }

    // == dev code ==
    @Override
    public ProductBoard getBoardById(Long id) {
        return productRepository.findBoardFirst(id);
    }
}
