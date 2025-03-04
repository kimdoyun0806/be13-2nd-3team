package com.beyond3.yyGang.category;

import com.beyond3.yyGang.nsupplement.NSupplementRegisterDto;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RequestMapping("/categories")
@RestController
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;

    @GetMapping("{categoryId}/products")
    @Operation(summary = "카테고리별 제품 조회", description = "카테고리에 속한 제품 목록을 조회한다.")
    public ResponseEntity<ProductListView> list(@PathVariable Long categoryId, Pageable pageable) {
        pageable = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize());
        ProductListView productList = categoryService.getProductInCategory(categoryId, pageable);
        return ResponseEntity.ok(productList);
    }

    @GetMapping("/products/{productId}")
    @Operation(summary = "제품 상세 조회", description = "제품 상세 정보를 조회한다.")
    public ResponseEntity<NSupplementRegisterDto> detail( @PathVariable Long productId) {

        NSupplementRegisterDto product = categoryService.getProduct(productId);
        return ResponseEntity.ok(product);
    }

}
