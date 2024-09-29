package net.projects.MovieManagement.repository;

import net.projects.MovieManagement.entity.Movie;
import net.projects.MovieManagement.util.MovieGenre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface MovieRespository extends JpaRepository<Movie, Long>, JpaSpecificationExecutor<Movie> {

}
