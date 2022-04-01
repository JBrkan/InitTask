package com.task.init.repositories;


import com.task.init.models.database.MovieCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;


@Repository
public interface MovieCategoryRepository extends JpaRepository<MovieCategory, Long> {
    @Transactional
    void deleteAllByMovieId(Long id);
}
