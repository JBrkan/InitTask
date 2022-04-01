package com.task.init.services;

import com.task.init.exceptions.CategoryAlreadyExistsException;
import com.task.init.models.database.Category;
import com.task.init.models.dtos.CategoryRequest;
import com.task.init.models.dtos.CategoryResponse;
import com.task.init.repositories.CategoryRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;
    private final ModelMapper mapper;

    public CategoryServiceImpl(CategoryRepository categoryRepository, ModelMapper mapper) {
        this.categoryRepository = categoryRepository;
        this.mapper = mapper;
    }

    @Override
    public List<CategoryResponse> getCategories() {
        List<CategoryResponse> categoryResponses = new ArrayList<>();
        for (Category category : categoryRepository.findAll()) {
            categoryResponses.add(mapper.map(category, CategoryResponse.class));
        }
        return categoryResponses;
    }

    @Override
    public CategoryResponse saveCategory(CategoryRequest categoryRequest) {
        String categoryName = categoryRequest.getName();
        if (categoryRepository.existsByName(categoryName)) {
            throw new CategoryAlreadyExistsException("Category already exists");
        }
        return mapper.map(categoryRepository.save(new Category(categoryName)), CategoryResponse.class);
    }

    @Override
    public CategoryResponse updateCategory(Long id, CategoryRequest categoryRequest) {
        String categoryName = categoryRequest.getName();

        if (categoryRepository.findByName(categoryName).isPresent()) {
            Category category = categoryRepository.findByName(categoryName).get();
            Category categoryById = categoryRepository.getById(id);
            if (!category.getId().equals(categoryById.getId())) {
                throw new CategoryAlreadyExistsException("Category already exists");
            }
        }

        if (categoryRepository.existsById(id)) {
            return mapper.map(categoryRepository.save(new Category(id, categoryName)), CategoryResponse.class);
        }

        return saveCategory(categoryRequest);
    }
}
