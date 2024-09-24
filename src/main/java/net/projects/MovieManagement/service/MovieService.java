package net.projects.MovieManagement.service;

import net.projects.MovieManagement.dto.request.SaveMovieDTO;
import net.projects.MovieManagement.dto.response.GetMovieDTO;
import net.projects.MovieManagement.entity.Movie;
import net.projects.MovieManagement.util.MovieGenre;

import java.util.List;

public interface MovieService {

    List<GetMovieDTO> findAll();

    List<GetMovieDTO> findAllByTitle(String title);

    List<GetMovieDTO> findAllByGenre(MovieGenre genre);

    List<GetMovieDTO> findAllByGenreAndTitle(MovieGenre genre, String title);

    GetMovieDTO findOneById(Long id);

    GetMovieDTO createOne(SaveMovieDTO saveMovieDTO);

    GetMovieDTO updateOneById(Long id, SaveMovieDTO saveMovieDTO);

    void deleteOneById(Long id);



}
