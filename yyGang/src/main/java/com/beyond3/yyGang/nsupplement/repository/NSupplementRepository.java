package com.beyond3.yyGang.nsupplement.repository;

import com.beyond3.yyGang.nsupplement.NSupplement;
import com.beyond3.yyGang.user.domain.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

public interface NSupplementRepository extends JpaRepository<NSupplement, Long>, NSupplementRepositoryCustom {

    // 상품 id로 검색
    Optional<NSupplement> findByproductId(Long id);

    List<NSupplement> findBySeller(User seller);

    List<NSupplement> findByProductName(String productName);

    // 상품 이름으로 나열하기-?
    @Query("SELECT n FROM NSupplement n ORDER BY n.productName DESC")
    List<NSupplement> findAllDesc();
}