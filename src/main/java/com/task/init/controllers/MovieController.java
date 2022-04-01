package com.task.init.controllers;

import com.task.init.exceptions.SearchParameterEmptyException;
import com.task.init.models.dtos.MovieRequest;
import com.task.init.models.dtos.MovieResponse;
import com.task.init.services.MovieService;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class MovieController {

    private final MovieService movieService;

    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    @GetMapping("/movies/{offSet}/{pageSize}")
    public Page<MovieResponse> getMovies(@PathVariable("offSet") int offset, @PathVariable("pageSize") int pageSize) {
        return movieService.getMoviesWithPagination(offset, pageSize);
    }

    @GetMapping("/movies/search")
    public List<MovieResponse> searchMovies(@RequestParam(defaultValue = "") String query) {
        if (query.isEmpty()) {
            throw new SearchParameterEmptyException("Search parameter is empty");
        }
        return movieService.searchMovies(query);
    }

    @PostMapping(value = "/movies", consumes = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<MovieResponse> postMovie(@RequestBody MovieRequest movieRequest) {
        movieRequest.validate();
        return ResponseEntity.status(HttpStatus.CREATED).body(movieService.saveMovie(movieRequest));
    }

    @PutMapping(value = "movies/{id}", consumes = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<MovieResponse> putMovie(@RequestBody MovieRequest movieRequest, @PathVariable("id") Long id) {
        movieRequest.validate();
        return ResponseEntity.status(HttpStatus.CREATED).body(movieService.updateMovie(movieRequest, id));
    }

    @DeleteMapping(value = "movies/{id}", consumes = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<MovieResponse> deleteMovie(@PathVariable("id") Long id) {
        movieService.deleteMovie(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
    }
}
