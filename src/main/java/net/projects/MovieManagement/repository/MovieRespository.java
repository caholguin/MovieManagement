package net.projects.MovieManagement.repository;

import net.projects.MovieManagement.entity.Movie;
import net.projects.MovieManagement.util.MovieGenre;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MovieRespository extends JpaRepository<Movie, Long> {

    List<Movie> findByTitleContaining(String title);

    List<Movie> findByGenre(MovieGenre genre);

    List<Movie> findByGenreAndTitleContaining(MovieGenre genre, String title);




}
