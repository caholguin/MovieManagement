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

    @Query("SELECT avg(r.rating) FROM Rating r WHERE r.movieId = ?1")
    double avgRatingByMovieId(Long id);

    @Query("SELECT min(r.rating) FROM Rating r WHERE r.movieId = ?1")
    int minRatingMovieId(Long id);

    int countByMovieId(Long id);

    @Query("select max(r.rating) from Rating r where r.movieId = ?1")
    int maxRatingByMovieId(Long id);

    int countByUserUsername(String username);

    @Query("SELECT avg(r.rating) FROM Rating r join r.user u WHERE u.username = ?1")
    double avgRatingByUsername(String username);

    @Query("SELECT min(r.rating) FROM Rating r join r.user u WHERE u.username = ?1")
    int minRatingByUsername(String username);

    @Query("SELECT max(r.rating) FROM Rating r join r.user u WHERE u.username = ?1")
    int maxRatingByUsername(String username);
}
