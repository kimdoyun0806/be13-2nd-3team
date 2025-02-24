package com.beyond3.yyGang.cart.repository;

import com.beyond3.yyGang.cart.domain.Cart;
import com.beyond3.yyGang.cart.domain.CartOption;
import com.beyond3.yyGang.cart.dto.CartListDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface CartOptionRepository extends JpaRepository<CartOption, Long> {

//    CartOption findByCart_CartIdAndNSupplement_ProductId(Long cartId, Long productId);

    @Query("SELECT co FROM CartOption co WHERE co.cart.cartId = :cartId AND co.nSupplement.productId = :productId")
    CartOption findByCartIdAndProductId(@Param("cartId") Long cartId, @Param("productId") Long productId);

    @Query("SELECT new com.beyond3.yyGang.cart.dto.CartListDto(co.cartOptionID, s.productName, s.price, co.quantity) " +
            "FROM CartOption co JOIN co.nSupplement s WHERE co.cart.cartId = :cartId")
    List<CartListDto> findByCartListDto(@Param("cartId") Long cartId);

}