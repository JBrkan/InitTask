package com.task.init.repositories;

import com.task.init.models.database.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MovieRepository extends JpaRepository<Movie, Long> {
    List<Movie> findByNameStartingWith(String name);
}
