package com.beyond3.yyGang.cart.service;

import com.beyond3.yyGang.cart.domain.Cart;
import com.beyond3.yyGang.cart.domain.CartOption;
import com.beyond3.yyGang.cart.dto.AddCartOptionRequestDto;
import com.beyond3.yyGang.cart.dto.CartOptionDto;
import com.beyond3.yyGang.cart.dto.CartResponseDto;
import com.beyond3.yyGang.cart.repository.CartOptionRepository;
import com.beyond3.yyGang.cart.repository.CartRepository;
import com.beyond3.yyGang.handler.exception.CartException;
import com.beyond3.yyGang.handler.exception.UserException;
import com.beyond3.yyGang.handler.message.*;
import com.beyond3.yyGang.nsupplement.NSupplement;
import com.beyond3.yyGang.nsupplement.repository.NSupplementRepository;
import com.beyond3.yyGang.user.domain.User;
import com.beyond3.yyGang.user.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class CartService {

    private final CartOptionRepository cartOptionRepository;
    private final CartRepository cartRepository;
    private final UserRepository userRepository;
    private final NSupplementRepository nSupplementRepository;

    @Transactional
    public CartResponseDto addCartOption(String userEmail, AddCartOptionRequestDto addCartOptionRequestDto) {

        NSupplement findNSupplement = nSupplementRepository.findById(addCartOptionRequestDto.getNSupplementId()).orElseThrow(() -> new CartException(CartExceptionMessage.PRODUCT_NOT_FOUND));
        Cart findCart = cartRepository.findByUserEmail(userEmail).orElseThrow(() -> new CartException(CartExceptionMessage.CART_NOT_FOUND));

        // 재고 확인
        if (findNSupplement.getStockQuantity() < addCartOptionRequestDto.getQuantity()) {
            throw new CartException(CartExceptionMessage.STOCK_QUANTITY_NOT_ENOUGH);
        }

        // 상품 중복 확인
        boolean isAlreadyInCart = cartOptionRepository.existsByCartAndNSupplement(findCart, findNSupplement);
        if (isAlreadyInCart) {
            throw new CartException(CartExceptionMessage.SUPPLEMENT_ALREADY_EXISTS);
        }

        // 카트 옵션 생성
        CartOption cartOption = CartOption.createCartOption(findCart, findNSupplement, addCartOptionRequestDto.getQuantity());

        // 카트 옵션 저장
        CartOption saveCartOption = cartOptionRepository.save(cartOption);

        // 카트 옵션 DTO 생성
        CartOptionDto cartOptionDto = CartOptionDto.fromCartOption(saveCartOption);

        // 카트 DTO 리턴
        return CartResponseDto.fromCart(findCart, List.of(cartOptionDto));
    }

    @Transactional
    public void deleteCartOption(Long cartOptionId/*, Long userId*/) {
        CartOption findCartOption = cartOptionRepository.findById(cartOptionId).orElseThrow(() -> new CartException(CartExceptionMessage.CART_OPTION_PRODUCT_NOT_FOUND));
        cartOptionRepository.delete(findCartOption);
    }

    @Transactional
    // 장바구니 상품 수량, 가격 업데이트
    public CartOptionDto updateCartProduct(Long cartOptionId, int count) {
        CartOption cartOption = cartOptionRepository.findById(cartOptionId)
                .orElseThrow(() -> new CartException(CartExceptionMessage.CART_NOT_FOUND));
        NSupplement nSupplement = cartOption.getNSupplement();

        // 재고 확인
        if (nSupplement.getStockQuantity() < count) {
            throw new CartException(CartExceptionMessage.STOCK_QUANTITY_NOT_ENOUGH);
        }

        // 수량 확인
        if (count < 1) {
            throw new CartException(CartExceptionMessage.CART_COUNT_MIN_VIOLATION);
        }

        cartOption.updateSupplement(count);
        return CartOptionDto.fromCartOption(cartOption);
    }

    public CartResponseDto getCart(String userEmail) {

        // 카트에 있는 카트옵션들을 가져올거라 패치조인 적용
        Cart findCart = cartRepository.findByUserEmailWithCartOptions(userEmail).orElseThrow(() -> new CartException(CartExceptionMessage.CART_NOT_FOUND));

        List<CartOption> cartOptions = findCart.getCartOptions();

        List<CartOptionDto> cartOptionDtoList = cartOptions.stream().map(cartOption ->
                CartOptionDto.fromCartOption(cartOption)).toList();

        return CartResponseDto.fromCart(findCart, cartOptionDtoList);
    }

}