package com.beyond3.yyGang.cart.repository;

import com.beyond3.yyGang.cart.domain.Cart;
import com.beyond3.yyGang.cart.domain.CartOption;
import com.beyond3.yyGang.nsupplement.NSupplement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartOptionRepository extends JpaRepository<CartOption, Long> {

    @Query("SELECT COUNT(c) > 0 FROM CartOption c WHERE c.cart = :cart AND c.nSupplement = :nSupplement")
    boolean existsByCartAndNSupplement(@Param("cart") Cart cart, @Param("nSupplement") NSupplement nSupplement);
}
