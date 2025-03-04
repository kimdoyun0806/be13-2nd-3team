package com.beyond3.yyGang.category;

import com.beyond3.yyGang.exception.message.CategoryEntityException;
import com.beyond3.yyGang.exception.message.ExceptionMessage;
import com.beyond3.yyGang.exception.message.ProductEntityException;
import com.beyond3.yyGang.nsupplement.NSupplement;
import com.beyond3.yyGang.nsupplement.NSupplementDto;
import com.beyond3.yyGang.nsupplement.NSupplementRegisterDto;
import com.beyond3.yyGang.nsupplement.NSupplementRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@AllArgsConstructor
@Transactional(readOnly = true)
public class CategoryService {
    private final NSupplementRepository nSupplementRepository;
    private final CategoryRepository categoryRepository;

    public CategoryDto getCategory(Long categoryId) {
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new CategoryEntityException(ExceptionMessage.CATEGORY_NOT_FOUND));
        return new CategoryDto(category);
    }

    /**
     * 최하단의 자식 카테고리를 조회하여, 해당 카테고리의 모든 상품 조회
     *
     * @param categoryId 카테고리 아이디
     * @param pageable   페이지
     * @return ProductListView
     */
    public ProductListView getProductInCategory(Long categoryId, Pageable pageable) {
        Page<NSupplement> products = nSupplementRepository.findByCategoryId(categoryId, pageable);
        List<NSupplementDto> productDtos = products.stream()
                .map(NSupplementDto::new)
                .collect(Collectors.toList());
        return new ProductListView(
                productDtos,
                products.getTotalElements(), // totalCount
                products.getTotalPages()      // totalPages
        );
    }

    //있어야 하는지 잘 모르겠음
    public NSupplementRegisterDto getProduct(Long productId) {
        NSupplement product = nSupplementRepository.findById(productId)
                .orElseThrow(() -> new ProductEntityException(ExceptionMessage.PRODUCT_NOT_FOUND));

        return new NSupplementRegisterDto(product);
    }
}