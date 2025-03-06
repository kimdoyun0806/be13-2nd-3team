package com.beyond3.yyGang.cart.service;

import com.beyond3.yyGang.cart.repository.CartOptionRepository;
import com.beyond3.yyGang.nsupplement.repository.NSupplementRepository;
import com.beyond3.yyGang.user.repository.UserRepository;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
@ExtendWith(SpringExtension.class)
class CartServiceTest {

    @Autowired
    CartService cartService;

    @Autowired
    NSupplementRepository nSupplementRepository;


    @Autowired
    UserRepository userRepository;

    @Autowired
    CartOptionRepository cartOptionRepository;

//    @Test
//    @DisplayName("장바구니 담기 테스트")
//    @Rollback(value = false)
//    public void addCart(){
//
//        User user = User.builder()
//                .name("홍길동")
//                .email("hello1234@naver.com")
//                .password("0000")
//                .age(23)
//                .phone("01011112222")
//                .gender(Gender.Male)
//                .address("서울특별시")
//                .role(Role_name.ADMIN)
//                .build();
//
//        userRepository.save(user);
//        user = userRepository.findByEmail(user.getEmail());
//
//        NSupplement nSupplement = NSupplement.builder()
//                .productName("테스트상품")
//                .price(10000)
//                .caution("공복에 주의")
//                .stockQuantity(50)
//                .brand("한일제약")
//                .build();
//
//        nSupplement = nSupplementRepository.save(nSupplement);
//
//        CartOptionDto cartOptionDto = new CartOptionDto();
//        cartOptionDto.setCount(5);
//        cartOptionDto.setProductId(nSupplement.getProductId());
//
//        Long cartOptionId = cartService.saveCartOption(cartOptionDto, user.getEmail());
//
//        CartOption cartOption = cartOptionRepository.findById(cartOptionId)
//                .orElseThrow(EntityNotFoundException::new);
//
//
//        assertEquals(nSupplement.getProductId(), cartOption.getNSupplement().getProductId());
//        assertEquals(cartOptionDto.getCount(),cartOption.getQuantity());
//    }
//


}