package net.projects.MovieManagement.repository;

import net.projects.MovieManagement.entity.Rating;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RatingRepository extends JpaRepository<Rating, Long> {

    List<Rating> findByMovieId(Long id);

    List<Rating> findByUserUsername(String username);
}
