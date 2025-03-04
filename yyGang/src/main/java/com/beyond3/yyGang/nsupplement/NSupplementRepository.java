package com.beyond3.yyGang.nsupplement;

import com.beyond3.yyGang.user.domain.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface NSupplementRepository extends JpaRepository<NSupplement, Long> {

    // 회원 아이디 - 단건 조회
    Optional<NSupplement> findByproductId(Long id);

    @Query("SELECT n FROM NSupplement n ORDER BY n.productName DESC")
    List<NSupplement> findAllDesc();

    @Query("""
        select distinct n
        from NSupplement n
        join ProductCategory pc on n = pc.nSupplement
        where pc.category.id = :categoryId
        """)
    Page<NSupplement> findByCategoryId(@Param("categoryId") Long categoryId, Pageable pageable);


}