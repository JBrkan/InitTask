package com.task.init.services;

import com.task.init.configurations.MovieConverter;
import com.task.init.models.database.Movie;
import com.task.init.models.dtos.MovieResponse;
import com.task.init.repositories.CategoryRepository;
import com.task.init.repositories.MovieCategoryRepository;
import com.task.init.repositories.MovieRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.ui.ModelMap;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class MovieServiceImplTest {

    @Mock
    private MovieRepository movieRepository;
    @Mock
    private CategoryRepository categoryRepository;
    @Mock
    private MovieCategoryRepository movieCategoryRepository;
    @Spy
    private ModelMapper modelMapper = new ModelMapper();

    @InjectMocks
    MovieServiceImpl movieService;

    @BeforeEach
    void setUp() {
        TypeMap<Movie, MovieResponse> typeMap = modelMapper.createTypeMap(Movie.class, MovieResponse.class);
        typeMap.addMappings(src -> src.using(new MovieConverter()).map(Movie::getMovieCategories, MovieResponse::setCategories));
    }

    @Test
    void getMoviesWithPagination() {
    }

    @Test
    void searchMovies() {
        //given
        String query = "M";
        when(movieRepository.findByNameStartingWith(query)).thenReturn(List.of(new Movie("Movie")));
        //when
        
        //then
    }

    @Test
    void saveMovie() {
    }

    @Test
    void updateMovie() {
    }

    @Test
    void deleteMovie() {
        //given
        Long id = 1L;
        //when
        when(movieRepository.existsById(id)).thenReturn(true);
        movieService.deleteMovie(id);
        //then
        verify(movieRepository).deleteById(id);
    }
}