package com.task.init.models.database;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @OneToMany(mappedBy = "movie", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<MovieCategory> movieCategories = new ArrayList<>();

    public Movie() {
    }

    public Movie(String name, List<MovieCategory> movieCategories) {
        this.name = name;
        this.movieCategories = movieCategories;
    }


    public Movie(String name) {
        this.name = name;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<MovieCategory> getMovieCategories() {
        return movieCategories;
    }

    public void setMovieCategories(List<MovieCategory> movieCategories) {
        this.movieCategories = movieCategories;
    }

    public void addMovieCategory(MovieCategory movieCategory) {
        this.movieCategories.add(movieCategory);
    }

    public void addMovieCategories(List<MovieCategory> movieCategories) {
        this.movieCategories.addAll(movieCategories);
    }


}
