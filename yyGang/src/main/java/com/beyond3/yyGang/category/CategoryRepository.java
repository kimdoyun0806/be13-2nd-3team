package com.beyond3.yyGang.category;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category, Long> {

    // CategoryId 한 번에 조회
    List<Category> findByIdIn(List<Long> categoryIds);

    // 자식 Category 목록 조회
    @Query("select Category from Category c where c.parent.id = :id")
    List<Category> findChildrenById(Long id);
}
