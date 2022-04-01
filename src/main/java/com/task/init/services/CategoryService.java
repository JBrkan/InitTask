package com.task.init.services;

import com.task.init.models.dtos.CategoryRequest;
import com.task.init.models.dtos.CategoryResponse;

import java.util.List;

public interface CategoryService {
    List<CategoryResponse> getCategories();

    CategoryResponse saveCategory(CategoryRequest categoryRequest);

    CategoryResponse updateCategory(Long id, CategoryRequest categoryRequest);
}
