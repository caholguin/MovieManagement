package net.projects.MovieManagement.repository;

import net.projects.MovieManagement.entity.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

public interface MovieRespository extends JpaRepository<Movie, Long>, JpaSpecificationExecutor<Movie> {

    /*@Query("select count(r.rating) from Movie m join m.ratings r where m.id = ?1")
    int countRatingsById(Long id);

    @Query("SELECT max(r.rating) FROM Movie m JOIN m.ratings r WHERE m.id = ?1")
    int maxRatingById(Long id);*/
}
