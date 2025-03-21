//package com.beyond3.yyGang.category;
//
//import lombok.Data;
//
//import java.util.ArrayList;
//import java.util.List;
//
//@Data
//public class CategoryDto {
//    private Long id;
//    private String name;
//    List<CategoryDto> children = new ArrayList<>();
//
//    public CategoryDto() {
//    }
//
//    public CategoryDto(Category category) {
//        this.id = category.getId();
//        this.name = category.getName();
//        setChildren(category.getChildren());
//    }
//
//    public CategoryDto(Long id, String name) {
//        this.id = id;
//        this.name = name;
//    }
//
//    // children -> CategoryDTO 변환
//    private void setChildren(List<Category> children) {
//        children.forEach(category -> {
//            CategoryDto child = new CategoryDto(category.getId(), category.getName());
//            this.children.add(child);
//        });
//    }
//}
