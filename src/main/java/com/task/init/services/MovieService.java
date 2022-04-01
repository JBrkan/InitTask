package com.task.init.services;

import com.task.init.models.dtos.MovieRequest;
import com.task.init.models.dtos.MovieResponse;
import org.springframework.data.domain.Page;

import java.util.List;


public interface MovieService {

    Page<MovieResponse> getMoviesWithPagination(int offset, int pageSize);

    List<MovieResponse> searchMovies(String search);

    MovieResponse saveMovie(MovieRequest movieRequest);

    MovieResponse updateMovie(MovieRequest movieRequest, Long id);

    void deleteMovie(Long id);
}
