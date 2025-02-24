package com.beyond3.yyGang.order.service;

import com.beyond3.yyGang.order.dto.OrderDto;
import com.beyond3.yyGang.nsupplement.domain.NSupplement;
import com.beyond3.yyGang.nsupplement.repository.NSupplementRepository;
import com.beyond3.yyGang.order.domain.Order;
import com.beyond3.yyGang.order.domain.OrderOption;
import com.beyond3.yyGang.order.repository.OrderRepository;
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
public class OrderService {
    private  final NSupplementRepository nSupplementRepository; // 상품 재고 변경을 위함
    private final UserRepository userRepository; // 유저를 불러와서 연결시키기 위함
    private final OrderRepository orderRepository; // 주문 저장


    @Transactional
    // 단품으로 상품 주문
    public Long order(OrderDto orderDto,String email) {


        // OrderOption List 객체 생성
        List<OrderOption> orderOptionList = new ArrayList<>();
        NSupplement nSupplement = nSupplementRepository.findById(orderDto.getProductId()).orElseThrow(EntityNotFoundException::new);
        orderOptionList.add(OrderOption.createOrderOption(nSupplement, orderDto.getCount()));


        // Order 객체 생성
        User user = userRepository.findByEmail(email);
        Order order = Order.createOrder(user, orderOptionList);

        return order.getOrderID();
    }

    // 주문 내역 조회
    // 주문한 유저 검증

    // 주문 취소
    public void orderCancel(Long orderId) {
        Order order = orderRepository.findById(orderId).orElseThrow(EntityNotFoundException::new);
        order.orderCancel();
    }

    // 장바구니 상품 일괄 주문
    public Long orders(List<OrderDto> orderDtoList, String email) {

        //로그인한 유저 조회
        User user = userRepository.findByEmail(email);

        // orderDto 객체를 이용하여 NSupplement 객체와 quantity 값을 얻어낸 뒤, 이를 이용하여 OrderOption 객체들을 생성
        List<OrderOption> orderOptionList = new ArrayList<>();
        for (OrderDto orderDto : orderDtoList) {
            NSupplement nSupplement = nSupplementRepository.findById(orderDto.getProductId()).orElseThrow(EntityNotFoundException::new);
            OrderOption orderOption = OrderOption.createOrderOption(nSupplement, orderDto.getCount());
            orderOptionList.add(orderOption);
        }

        Order order = Order.createOrder(user, orderOptionList);
        orderRepository.save(order);
        return order.getOrderID();

    }


}
