package com.task.init.configurations;

import com.task.init.models.database.Category;
import com.task.init.models.database.MovieCategory;
import org.modelmapper.AbstractConverter;

import java.util.List;
import java.util.stream.Collectors;

public class MovieConverter extends AbstractConverter<List<MovieCategory>, List<Category>> {

    @Override
    protected List<Category> convert(List<MovieCategory> movieCategories) {
        return movieCategories.stream().map(MovieCategory::getCategory).collect(Collectors.toList());
    }

}
