package com.task.init.models.dtos;

import com.task.init.exceptions.FaultyRequestException;

public class CategoryRequest implements ValidateRequest {
    private String name;

    public CategoryRequest(String name) {
        this.name = name;
    }

    public CategoryRequest() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public void validate() {
        if (name == null || name.equals("")) {
            throw new FaultyRequestException("Category name is empty");
        }
    }
}
