package com.beyond3.yyGang.cart.service;

import com.beyond3.yyGang.cart.domain.Cart;
import com.beyond3.yyGang.cart.domain.CartOption;
import com.beyond3.yyGang.cart.dto.CartListDto;
import com.beyond3.yyGang.cart.dto.CartOptionDto;
import com.beyond3.yyGang.cart.dto.CartOrderDto;
import com.beyond3.yyGang.cart.repository.CartOptionRepository;
import com.beyond3.yyGang.cart.repository.CartRepository;
import com.beyond3.yyGang.nsupplement.domain.NSupplement;
import com.beyond3.yyGang.nsupplement.repository.NSupplementRepository;
import com.beyond3.yyGang.order.dto.OrderDto;
import com.beyond3.yyGang.order.service.OrderService;
import com.beyond3.yyGang.user.domain.User;
import com.beyond3.yyGang.user.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class CartService {


    private final CartRepository cartRepository;
    private final CartOptionRepository cartOptionRepository;
    private final UserRepository userRepository;
    private final NSupplementRepository nSupplementRepository;
    private final OrderService orderService;

    // 장바구니 담기
    @Transactional
    public Long addCart(CartOptionDto cartOptionDto, String email) {

        User user = userRepository.findByEmail(email);

        if (user == null) {
            throw new EntityNotFoundException("User not found with email: " + email);
        }


        Cart cart = cartRepository.findByUser_UserId(user.getUserId());

        // 장바구니가 존재하지 않을 때 (회원가입을 하면 카트 하나를 바로 생성해야 하는데 이 부분은 확인할 것)
        if (cart == null) {
            cart = Cart.createCart(user);
            cartRepository.save(cart);
        }

        // 영양제 찾기
        NSupplement nSupplement = nSupplementRepository.findById(cartOptionDto.getProductId())
                .orElseThrow(() -> new EntityNotFoundException("NSupplement not found with id: " + cartOptionDto.getProductId()));

//        NSupplement nSupplement = nSupplementRepository.findById(cartOptionDto.getProductId()).orElseThrow(EntityNotFoundException::new);
        CartOption cartOption = cartOptionRepository.findByCartIdAndProductId(cart.getCartId(), nSupplement.getProductId());


        //해당 상품이 장바구니에 없으면 생성 후 추가
        if (cartOption == null) {
            cartOption = CartOption.createCartSupplements(cart, nSupplement, cartOptionDto.getCount());
            cartOptionRepository.save(cartOption);

        }

        //상품이 이미 있으면 수량 업데이트
        else {
            cartOption.addQuantity(cartOptionDto.getCount());
        }

        return cartOption.getCartOptionID();
    }

    // 장바구니 조회 기능
    public List<CartListDto> getCartList(String email) {

        List<CartListDto> cartListDtos = new ArrayList<>();

        User user = userRepository.findByEmail(email);
        Cart cart = cartRepository.findByUser_UserId(user.getUserId());

        // 장바구니가 없다면 빈 리스트를 반환 회원과 장바구니는 동시에 생성되니 필요는 없겠지만
        // 혹시 모를 예외처리(NullPointException)를 위함
        if (cart == null) {
            return cartListDtos;
        }

        // CartOptionRepository 확인할 것
        cartListDtos = cartOptionRepository.findByCartListDto(cart.getCartId());
        return cartListDtos;
    }


    @Transactional
    // 장바구니 상품 수량 업데이트
    public void updateCartProductQuantity(Long cartOptionId, int count) {
        CartOption cartOption = cartOptionRepository.findById(cartOptionId).orElseThrow(EntityNotFoundException::new);
        cartOption.updateQuantity(count);
    }

    @Transactional
    // 장바구니 상품 삭제
    public void deleteCartProduct(Long cartOptionId) {
        CartOption cartOption = cartOptionRepository.findById(cartOptionId).orElseThrow(EntityNotFoundException::new);
        cartOptionRepository.delete(cartOption);
    }

    @Transactional
    //장바구니 상품 일괄 주문
    public Long orderCartProduct(List<CartOrderDto> cartOrderDtoList, String email) {

        List<OrderDto> orderDtoList = new ArrayList<>();

        /*
            1. OrderDto 갹체 생성
            2. CartOrderDto에 있는 상품Id를 사용해서 CartOption 조회
            3. OrderDto에서 상품Id, 수량 설정
            4. OrderDto를 CartOrderList에 추가
         */
        for(CartOrderDto cartOrderDto : cartOrderDtoList) {
            OrderDto orderDto = new OrderDto();
            CartOption cartOption = cartOptionRepository.findById(cartOrderDto.getCartProductId()).orElseThrow(EntityNotFoundException::new);
            orderDto.setProductId(cartOption.getNSupplement().getProductId());
            orderDto.setCount(cartOption.getQuantity());
            orderDtoList.add(orderDto);
        }

        Long orderId = orderService.orders(orderDtoList, email);

        // 주문한 장바구니 상품 제거
        for(CartOrderDto cartOrderDto  : cartOrderDtoList) {
            CartOption cartOption = cartOptionRepository.findById(cartOrderDto.getCartProductId()).orElseThrow(EntityNotFoundException::new);
            cartOptionRepository.delete(cartOption);
        }

        // 주문 아이디 반환
        return orderId;
    }

    // 장바구니 상품 개별 주문?

}

