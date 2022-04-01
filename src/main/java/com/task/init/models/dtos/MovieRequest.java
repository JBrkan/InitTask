package com.task.init.models.dtos;

import com.task.init.exceptions.FaultyRequestException;

import java.util.List;

public class MovieRequest implements ValidateRequest {
    private String name;
    private List<String> categories;

    public MovieRequest() {
    }

    public MovieRequest(String name, List<String> categories) {
        this.name = name;
        this.categories = categories;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getCategories() {
        return categories;
    }

    public void setCategories(List<String> categories) {
        this.categories = categories;
    }

    @Override
    public void validate() {
        if (name == null || name.equals("")) {
            throw new FaultyRequestException("Movie name is empty");
        }
    }
}

