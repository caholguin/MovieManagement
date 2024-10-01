package net.projects.MovieManagement.repository;

import net.projects.MovieManagement.entity.Rating;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RatingRepository extends JpaRepository<Rating, Long> {

    Page<Rating> findByMovieId(Long id, Pageable pageable);

    Page<Rating> findByUserUsername(String username,Pageable pageable);

    boolean existsByMovieIdAndUserUsername(Long movieId, String username);

}
