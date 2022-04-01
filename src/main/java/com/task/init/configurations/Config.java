package com.task.init.configurations;

import com.task.init.models.database.Movie;
import com.task.init.models.dtos.MovieResponse;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Config {

    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();

        TypeMap<Movie, MovieResponse> typeMap = modelMapper.createTypeMap(Movie.class, MovieResponse.class);
        typeMap.addMappings(src -> src.using(new MovieConverter()).map(Movie::getMovieCategories, MovieResponse::setCategories));

        return modelMapper;
    }

}
