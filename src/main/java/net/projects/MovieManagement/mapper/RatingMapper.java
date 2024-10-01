package net.projects.MovieManagement.mapper;

import net.projects.MovieManagement.dto.request.SaveRatingDTO;
import net.projects.MovieManagement.dto.response.GetMovieDTO;
import net.projects.MovieManagement.dto.response.GetRatingDTO;
import net.projects.MovieManagement.dto.response.GetUserDTO;
import net.projects.MovieManagement.entity.Rating;


import java.util.List;

public class RatingMapper {


    public static GetRatingDTO toDto(Rating rating) {

        if (rating == null) return null;

        String movieTitle = rating.getMovie() != null ? rating.getMovie().getTitle() : null;
        String username = rating.getUser() != null ? rating.getUser().getUsername() : null;

        GetRatingDTO getRatingDTO = new GetRatingDTO();
        getRatingDTO.setId(rating.getId());
        getRatingDTO.setMovieId(rating.getMovieId());
        getRatingDTO.setMovieTitle(movieTitle);
        getRatingDTO.setUserId(rating.getUserId());
        getRatingDTO.setUsername(username);
        getRatingDTO.setRating(rating.getRating());

        return getRatingDTO;
    }

    public static Rating toEntity(SaveRatingDTO saveRatingDTO, Long userId){

        if (saveRatingDTO == null) return null;

        Rating rating = new Rating();
        rating.setMovieId(saveRatingDTO.getMovieId());
        rating.setUserId(userId);
        rating.setRating(saveRatingDTO.getRating());

        return rating;
    }

    public static GetMovieDTO.GetRatingDTO toGetMovieRatingDTO(Rating rating){

        if (rating == null) return null;

        String username = rating.getUser() != null ? rating.getUser().getUsername() : null;

        return new GetMovieDTO.GetRatingDTO(
                rating.getId(),
                rating.getRating(),
                username
        );
    }

    public static GetUserDTO.GetRatingDTO toGetUserRatingDTO(Rating rating){

        if (rating == null) return null;

        String movieTitle = rating.getMovie() != null ? rating.getMovie().getTitle() : null;

        return new GetUserDTO.GetRatingDTO(
            rating.getId(),
            movieTitle,
            rating.getMovieId(),
            rating.getRating()
        );
    }

    public static List<GetMovieDTO.GetRatingDTO> toGetMovieRatingsDTO(List<Rating> ratings){
        if (ratings == null) return null;

        return ratings.stream()
                .map(RatingMapper::toGetMovieRatingDTO)
                .toList();
    }

    public static List<GetUserDTO.GetRatingDTO> toGetUserRatingsDTO(List<Rating> ratings){
        if (ratings == null) return null;

        return ratings.stream()
                .map(RatingMapper::toGetUserRatingDTO)
                .toList();
    }


    public static void updateEntity(Rating rating, SaveRatingDTO saveRatingDTO,Long userId){
        if (rating == null || saveRatingDTO == null) return;

        rating.setMovieId(saveRatingDTO.getMovieId());
        rating.setUserId(userId);
        rating.setRating(saveRatingDTO.getRating());
    }
}
