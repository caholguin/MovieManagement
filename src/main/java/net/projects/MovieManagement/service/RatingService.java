package net.projects.MovieManagement.service;

import net.projects.MovieManagement.entity.Rating;

import java.util.List;

public interface RatingService {

    List<Rating> getAll();

    List<Rating> findAllByMovieId(Long movieId);

    List<Rating> findAllByUsername(String username);

    Rating getById(Long id);

    Rating createOne(Rating rating);

    Rating updateOneById(Long id,Rating rating);

    void deleteOneById(Long id);

}
