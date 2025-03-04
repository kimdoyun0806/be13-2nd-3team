package com.beyond3.yyGang.category;

import com.beyond3.yyGang.nsupplement.NSupplementDto;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class ProductListView {
    private List<NSupplementDto> products = new ArrayList<>();
    private long totalCount;
    private int totalPages;

    public ProductListView() {
    }

    public ProductListView(List<NSupplementDto> products, long totalCount, int totalPages) {
        this.products = products;
        this.totalCount = totalCount;
        this.totalPages = totalPages;
    }
}