package com.beyond3.yyGang.cart.repository;

import com.beyond3.yyGang.cart.domain.Cart;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface CartRepository extends JpaRepository<Cart, Long> {
    Cart findByUser_UserId(Long userId);


}