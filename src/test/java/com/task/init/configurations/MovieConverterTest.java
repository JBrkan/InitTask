package com.task.init.configurations;

import com.task.init.models.database.Category;
import com.task.init.models.database.Movie;
import com.task.init.models.database.MovieCategory;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MovieConverterTest {

    private MovieConverter movieConverter = new MovieConverter();

    @Test
    void convert() {
        //given
        Movie movie = new Movie("Movie");
        Category category = new Category("Category");
        List<MovieCategory> movieCategories = new ArrayList<>();
        movieCategories.add(new MovieCategory(movie, category));
        List<Category> categories = new ArrayList<>();
        categories.add(category);
        //when
        List<Category> convertedCategories = movieConverter.convert(movieCategories);
        //then
        assertEquals(categories, convertedCategories);
    }
}