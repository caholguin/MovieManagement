package net.projects.MovieManagement.repository.specification;

import jakarta.persistence.criteria.*;
import net.projects.MovieManagement.dto.request.MovieSearchCriteriaDTO;
import net.projects.MovieManagement.entity.Movie;
import net.projects.MovieManagement.entity.Rating;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class FindAllMovieEspecification implements Specification<Movie> {

    private final MovieSearchCriteriaDTO searchCriteria;

    public FindAllMovieEspecification(MovieSearchCriteriaDTO searchCriteria){
        this.searchCriteria = searchCriteria;
    }

    @Override
    public Predicate toPredicate(Root<Movie> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder){

        List<Predicate> predicates = new ArrayList<>();

        if (StringUtils.hasText(this.searchCriteria.getTitle())) {
            Predicate titleLike = criteriaBuilder.like(root.get("title"), "%" + this.searchCriteria.getTitle() + "%");
            predicates.add(titleLike);
        }

        if (searchCriteria.getGenre() != null) {
            Predicate genreEqual = criteriaBuilder.equal(root.get("genre"), this.searchCriteria.getGenre());
            predicates.add(genreEqual);
        }

        if (searchCriteria.getMinReleaseYear() != null && searchCriteria.getMinReleaseYear() > 0) {
            Predicate releaseYearGreaterThan = criteriaBuilder.greaterThanOrEqualTo(root.get("releaseYear"), this.searchCriteria.getMinReleaseYear());
            predicates.add(releaseYearGreaterThan);
        }

        if (searchCriteria.getMaxReleaseYear() != null && searchCriteria.getMaxReleaseYear() > 0) {
            Predicate releaseYearLessThanEqual = criteriaBuilder.lessThanOrEqualTo(root.get("releaseYear"), this.searchCriteria.getMaxReleaseYear());
            predicates.add(releaseYearLessThanEqual);
        }

        if(this.searchCriteria.getMinAverageRating() != null && this.searchCriteria.getMinAverageRating() > 0) {
            Subquery<Double> averageRatingSubquery = getAverageRatingSubquery(root, query, criteriaBuilder);

            Predicate averageRatingGreaterThanEqual = criteriaBuilder.greaterThanOrEqualTo(averageRatingSubquery,this.searchCriteria.getMinAverageRating().doubleValue());
            predicates.add(averageRatingGreaterThanEqual);
        }

        return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
    }

    private static Subquery<Double> getAverageRatingSubquery(Root<Movie> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder){

        Subquery<Double> averageRatingSubquery = query.subquery(Double.class);
        Root<Rating> ratingRoot = averageRatingSubquery.from(Rating.class);

        averageRatingSubquery.select(criteriaBuilder.avg(ratingRoot.get("rating")));

        Predicate movieIdEqual = criteriaBuilder.equal(root.get("id"), ratingRoot.get("movieId"));

        averageRatingSubquery.where(movieIdEqual);
        return averageRatingSubquery;
    }
}
