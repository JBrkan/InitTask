package com.task.init.models.dtos;


import com.task.init.models.database.Category;

import java.util.List;

public class MovieResponse {
    private Long id;
    private String name;
    private List<Category> categories;

    public MovieResponse() {
    }

    public MovieResponse(Long id, String name, List<Category> categories) {
        this.id = id;
        this.name = name;
        this.categories = categories;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Category> getCategories() {
        return categories;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }
}
