package com.beyond3.yyGang.cart.controller;

import com.beyond3.yyGang.cart.domain.Cart;
import com.beyond3.yyGang.cart.domain.CartOption;
import com.beyond3.yyGang.cart.dto.CartListDto;
import com.beyond3.yyGang.cart.dto.CartOptionDto;
import com.beyond3.yyGang.cart.repository.CartOptionRepository;
import com.beyond3.yyGang.cart.repository.CartRepository;
import com.beyond3.yyGang.cart.service.CartService;
import com.beyond3.yyGang.security.JwtTokenProvider;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/cart")
@RequiredArgsConstructor
public class CartController {

    private final CartService cartService;
    private final CartRepository cartRepository;
    private final CartOptionRepository cartOptionRepository;
    private final JwtTokenProvider jwtTokenProvider;

    // 로그인한 사용자의 장바구니 조회
    @GetMapping
    public ResponseEntity<List<CartListDto>> getUserCart(@RequestHeader("Authorization") String token) {
        String userEmail = getUserEmailFromToken(token);

        Cart findCart = cartRepository.findByUserEmail(userEmail).orElseThrow(() -> new EntityNotFoundException("cart not found"));
        List<CartListDto> cartListDtoByCartId = cartOptionRepository.findCartListDtoByCartId(findCart.getCartId());

        return ResponseEntity.ok(cartListDtoByCartId);
    }


    // 장바구니 삭제
    @DeleteMapping("/{cartOptionId}")
    public ResponseEntity<Long> deleteCartOption(@PathVariable Long cartOptionId
                                                 /*,@RequestHeader("Authorization") String token*/) {

        cartOptionRepository.findById(cartOptionId)
                .orElseThrow(() -> new EntityNotFoundException("해당 장바구니 품목을 찾을 수 없습니다."));

        cartService.deleteCartOption(cartOptionId);
        return ResponseEntity.ok(cartOptionId);
    }

    // 장바구니 상품 수량,가격 변경
    @PutMapping("/{cartOptionId}")
    public ResponseEntity<CartOptionDto> updateCartOptionQuantity(@PathVariable Long cartOptionId,
                                                         @RequestParam int quantity
                                                         /*,@RequestHeader("Authorization") String token*/) {


//        if(quantity <= 0 ) {
//            return new ResponseEntity<CartOptionDto>("최소 1개 이상 담아주세요", HttpStatus.BAD_REQUEST);
//        }
        // 수량, 가격 업데이트
        cartService.updateCartProduct(cartOptionId, quantity);

        CartOption updateCartOption = cartOptionRepository.findById(cartOptionId).orElseThrow(EntityNotFoundException::new);
        CartOptionDto cartOptionDto = new CartOptionDto();
        cartOptionDto.setNSupplementId(updateCartOption.getNSupplement().getProductId());
        cartOptionDto.setQuantity(updateCartOption.getQuantity());
        cartOptionDto.setPrice(updateCartOption.getPrice());  // 변경된 가격

        return ResponseEntity.ok(cartOptionDto);
    }

    private String getUserEmailFromToken(String token){
        String trimToken = token.substring(7).trim();

        if(!jwtTokenProvider.validateToken(trimToken)){
            // 토큰이 유효하지 않은 경우
            throw new UsernameNotFoundException("유효하지 않은 토큰입니다.");
        }
        return jwtTokenProvider.getAuthentication(trimToken).getName();
    }
}
