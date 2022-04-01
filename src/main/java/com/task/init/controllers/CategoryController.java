package com.task.init.controllers;

import com.task.init.models.dtos.CategoryRequest;
import com.task.init.models.dtos.CategoryResponse;
import com.task.init.services.CategoryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("/categories")
    public ResponseEntity<List<CategoryResponse>> getCategories() {
        return ResponseEntity.ok().body(categoryService.getCategories());
    }

    @PostMapping(value = "/categories", consumes = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<CategoryResponse> saveCategory(@RequestBody CategoryRequest categoryRequest) {
        categoryRequest.validate();
        return ResponseEntity.status(HttpStatus.CREATED).body(categoryService.saveCategory(categoryRequest));
    }

    @PutMapping(value = "/categories/{id}", consumes = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<CategoryResponse> updateCategory(@RequestBody CategoryRequest categoryRequest, @PathVariable("id") Long id) {
        categoryRequest.validate();
        return ResponseEntity.status(HttpStatus.CREATED).body(categoryService.updateCategory(id, categoryRequest));
    }
}
