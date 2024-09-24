package net.projects.MovieManagement.mapper;

import net.projects.MovieManagement.dto.request.SaveMovieDTO;
import net.projects.MovieManagement.dto.response.GetMovieDTO;
import net.projects.MovieManagement.entity.Movie;

import java.util.List;

public class MovieMapper {

    public static GetMovieDTO toDto(Movie movie) {

        if (movie == null) return null;

        GetMovieDTO movieDTO = new GetMovieDTO();
        movieDTO.setId(movie.getId());
        movieDTO.setTitle(movie.getTitle());
        movieDTO.setDirector(movie.getDirector());
        movieDTO.setGenre(movie.getGenre());
        movieDTO.setReleaseYear(movie.getReleaseYear());
        movieDTO.setRatings(RatingMapper.toGetMovieRatingsDTO(movie.getRatings()));

        return movieDTO;
    }

    public static List<GetMovieDTO> toDtoList(List<Movie> movies){

        if (movies == null) return null;

        return movies.stream()
                .map(MovieMapper::toDto)
                .toList();
    }

    public static Movie toEntity(SaveMovieDTO saveMovieDTO) {

        if (saveMovieDTO == null) return null;

        Movie movie = new Movie();
        movie.setTitle(saveMovieDTO.getTitle());
        movie.setDirector(saveMovieDTO.getDirector());
        movie.setReleaseYear(saveMovieDTO.getReleaseYear());
        movie.setGenre(saveMovieDTO.getGenre());

        return movie;
    }

    public static void updateEntity(Movie oldMovie, SaveMovieDTO saveMovieDTO){

        if (oldMovie == null || saveMovieDTO == null) return;

        oldMovie.setGenre(saveMovieDTO.getGenre());
        oldMovie.setReleaseYear(saveMovieDTO.getReleaseYear());
        oldMovie.setTitle(saveMovieDTO.getTitle());
        oldMovie.setDirector(saveMovieDTO.getDirector());
    }
}
