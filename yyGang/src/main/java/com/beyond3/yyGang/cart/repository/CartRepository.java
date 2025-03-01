package com.beyond3.yyGang.cart.repository;

import com.beyond3.yyGang.cart.domain.Cart;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

public interface CartRepository extends JpaRepository<Cart, Long> {

    // 페치 조인 사용 고려, 아마 사용해야할듯
    @Query("select c from Cart c where c.user.userId = :userId")
    Optional<Cart> findByUserId(@Param("userId") Long userId);
//    @Query("select c from Cart c where c.user.email = :userEmail")
//    Optional<Cart> findByUserEmail(@Param("userEmail") String email);

    @Query("select c from Cart c where c.user.email = :userEmail")
    Optional<Cart> findByUserEmail(@Param("userEmail") String email);

    @Query("select c from Cart c join fetch c.cartOptions co join fetch co.nSupplement ns where c.user.email = :userEmail")
    Optional<Cart> findByUserEmailWithCartOptions(@Param("userEmail") String email);
}