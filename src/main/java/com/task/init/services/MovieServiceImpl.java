package com.task.init.services;

import com.task.init.exceptions.FaultyRequestException;
import com.task.init.models.database.Category;
import com.task.init.models.database.Movie;
import com.task.init.models.database.MovieCategory;
import com.task.init.models.dtos.MovieRequest;
import com.task.init.models.dtos.MovieResponse;
import com.task.init.repositories.CategoryRepository;
import com.task.init.repositories.MovieCategoryRepository;
import com.task.init.repositories.MovieRepository;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MovieServiceImpl implements MovieService {

    private final MovieRepository movieRepository;
    private final CategoryRepository categoryRepository;
    private final MovieCategoryRepository movieCategoryRepository;
    private final ModelMapper mapper;

    public MovieServiceImpl(MovieRepository movieRepository,
                            CategoryRepository categoryRepository, ModelMapper mapper,
                            MovieCategoryRepository movieCategoryRepository) {
        this.movieRepository = movieRepository;
        this.categoryRepository = categoryRepository;
        this.mapper = mapper;
        this.movieCategoryRepository = movieCategoryRepository;
    }

    @Override
    public Page<MovieResponse> getMoviesWithPagination(int offset, int pageSize) {
        return movieRepository.findAll(PageRequest.of(offset, pageSize)).map(this::convert);
    }

    private MovieResponse convert(Movie movie) {
        return mapper.map(movie, MovieResponse.class);
    }

    @Override
    public List<MovieResponse> searchMovies(String query) {
        List<MovieResponse> responseList = new ArrayList<>();
        List<Movie> movies = movieRepository.findByNameStartingWith(query);
        for (Movie movie : movies) {
            responseList.add(mapper.map(movie, MovieResponse.class));
        }
        return responseList;
    }

    @Override
    public MovieResponse saveMovie(MovieRequest movieRequest) {
        String movieName = movieRequest.getName();
        List<Category> categories = getValidatedCategoryList(movieRequest);
        Movie movie = new Movie(movieName);
        for (Category category : categories) {
            movie.addMovieCategory(new MovieCategory(movie, category));
        }
        return mapper.map(movieRepository.save(movie), MovieResponse.class);
    }

    @Override
    public MovieResponse updateMovie(MovieRequest movieRequest, Long id) {
        String movieName = movieRequest.getName();
        List<Category> categories = getValidatedCategoryList(movieRequest);

        if (movieRepository.existsById(id)) {
            Movie movie = movieRepository.findById(id).get();
            movie.setName(movieName);
            movieCategoryRepository.deleteAllByMovieId(movie.getId());
            for (Category category : categories) {
                movie.addMovieCategory(new MovieCategory(movie, category));
            }
            return mapper.map(movieRepository.save(movie), MovieResponse.class);
        }
        return saveMovie(movieRequest);
    }

    private List<Category> getValidatedCategoryList(MovieRequest movieRequest) {
        List<Category> categories = categoryRepository.findAllByNameIn(movieRequest.getCategories());
        if (categories.size() != movieRequest.getCategories().size()) {
            throw new FaultyRequestException("Request contains non existing categories");
        }
        return categories;
    }

    @Override
    public void deleteMovie(Long id) {
        if (movieRepository.existsById(id)) {
            movieRepository.deleteById(id);
        }
    }


}
