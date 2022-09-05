package com.youngjo.ssg.domain.user.service;

import com.youngjo.ssg.domain.product.domain.MainProduct;
import com.youngjo.ssg.domain.product.domain.ProductBoard;
import com.youngjo.ssg.domain.product.repository.ProductRepository;
import com.youngjo.ssg.domain.user.domain.NormalCart;
import com.youngjo.ssg.domain.user.dto.request.PdtInCartReqDto;
import com.youngjo.ssg.domain.user.dto.response.PdtInCartResDto;
import com.youngjo.ssg.domain.user.repository.NormalCartRepository;
import com.youngjo.ssg.domain.user.repository.UserRepository;
import com.youngjo.ssg.global.security.bean.ClientInfoLoader;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class CartServiceImpl implements CartService {
    private final ProductRepository productRepository;
    private final NormalCartRepository normalCartRepository;
    private final UserRepository userRepository;
    private final ClientInfoLoader clientInfoLoader;

    @Override
    public void addProductToCart(List<PdtInCartReqDto> pdtDtoList) {
        List<NormalCart> myCartList = normalCartRepository.findAllNormalCartByUserId(clientInfoLoader.getUserId());
        List<Long> pdtIds = myCartList.stream().map(cart -> cart.getMainProduct().getId()).collect(Collectors.toList());
        Map<Long, MainProduct> pdtMap = productRepository.findAllMainPdtMapByIds(
                pdtDtoList.stream().map(PdtInCartReqDto::getPdtId).collect(Collectors.toList())
        );
        Map<MainProduct, List<NormalCart>> pdtCartMap = myCartList.stream().collect(Collectors.groupingBy(NormalCart::getMainProduct));

        pdtDtoList.forEach(pdt -> {
                    if (!pdtIds.contains(pdt.getPdtId())) {
                        normalCartRepository.saveNormalCart(
                                NormalCart.builder()
                                        .pdtQty(pdt.getPdtQty())
                                        .build()
                                        .linkToMainProduct(pdtMap.get(pdt.getPdtId()))
                                        .linkToUser(userRepository.findUserById(clientInfoLoader.getUserId())));
                    } else {
                        List<NormalCart> normalCartListByMap = pdtCartMap.get(pdtMap.get(pdt.getPdtId()));
                        normalCartListByMap.get(0).addPdtQty(pdt.getPdtQty());
                    }
                }
        );
    }

    @Transactional(readOnly = true)
    @Override
    public List<PdtInCartResDto> getCartPdtListByPdtIds(List<Long> pdtIds) {
        // <pdt.id, board>
        Map<Long, ProductBoard> boardMap = productRepository.findAllBoardMapByPdtIds(pdtIds);
        // <pdt.id, pdt>
        Map<Long, MainProduct> pdtMap = productRepository.findAllMainPdtMapByIds(pdtIds);

        return pdtIds.stream().distinct()
                .map(pdtId -> new PdtInCartResDto(boardMap.get(pdtId), pdtMap.get(pdtId), null))
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    @Override
    public List<PdtInCartResDto> getUserPdtListInCart() {
        List<NormalCart> myCartList = normalCartRepository.findAllNormalCartByUserId(clientInfoLoader.getUserId());
        List<Long> pdtIds = myCartList.stream().map(cart -> cart.getMainProduct().getId()).collect(Collectors.toList());

        // <pdt.id, board>
        Map<Long, ProductBoard> boardMap = productRepository.findAllBoardMapByPdtIds(pdtIds);
        // <pdt.id, pdt>
        Map<Long, MainProduct> pdtMap = productRepository.findAllMainPdtMapByIds(pdtIds);

        return myCartList.stream()
                .map(cart -> new PdtInCartResDto(boardMap.get(cart.getMainProduct().getId()), pdtMap.get(cart.getMainProduct().getId()), cart))
                .collect(Collectors.toList());
    }

    @Override
    public void delSoldOutPdtInUserCart() {
        normalCartRepository.removeSoldOutPdtInUserCart(clientInfoLoader.getUserId());
    }

    @Override
    public void delPdtInUserCart(Long pdtId) {
        normalCartRepository.removePdtInUserCart(pdtId, clientInfoLoader.getUserId());
    }

    @Override
    public Boolean updatePdtInUserCart(PdtInCartReqDto pdtDto) {
        NormalCart cart = normalCartRepository.findPdtInUserCartByPdtId(pdtDto.getPdtId(), clientInfoLoader.getUserId());
        if (cart != null && cart.getMainProduct().getStock() >= pdtDto.getPdtQty()) {
            cart.setPdtQty(pdtDto.getPdtQty());
            return true;
        }
        return false;
    }
}
