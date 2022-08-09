package com.youngjo.ssg.domain.product.service;

import com.youngjo.ssg.domain.product.domain.ProductBoard;
import com.youngjo.ssg.domain.product.dto.request.PdtBoardAddReqDto;
import com.youngjo.ssg.domain.product.repository.CategoryL4Repository;
import com.youngjo.ssg.domain.product.repository.ProductRepository;
import com.youngjo.ssg.global.enumeration.SalesSite;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final CategoryL4Repository categoryL4Repository;


    @Override
    public void addPdtBoard(PdtBoardAddReqDto pdtBoardAddReqDto) {
        productRepository.save(
                ProductBoard.builder()
                        .title(pdtBoardAddReqDto.getTitle())
                        .brand(pdtBoardAddReqDto.getBrand())
                        .salesSite(SalesSite.CONSTRUCT.findInstance(pdtBoardAddReqDto.getSalesSite()))
                        .shippingInfo(pdtBoardAddReqDto.getShippingInfo())

                        .pdtName(pdtBoardAddReqDto.getPdtName())
                        .optionName1(pdtBoardAddReqDto.getOptionName1())
                        .optionName2(pdtBoardAddReqDto.getOptionName2())

                        .thumbImgList(pdtBoardAddReqDto.getThumbImgList())
                        .detailImgList(pdtBoardAddReqDto.getDetailImgList())
                        .requiredInfo(pdtBoardAddReqDto.getRequiredInfo())
                        .exchangeRefundAddress(pdtBoardAddReqDto.getExchangeRefundAddress())
                        .categoryL4(categoryL4Repository.findById(pdtBoardAddReqDto.getCtgL4Id()).get())
                        .build()

                        .linkToProductList(pdtBoardAddReqDto.getProductList())
        );
    }

    // == dev code ==
    @Override
    public ProductBoard getBoardFirst() {
        return productRepository.findBoard();
    }
}
