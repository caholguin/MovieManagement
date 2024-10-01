package net.projects.MovieManagement.dto.request;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;

import java.io.Serializable;

public class SaveRatingDTO implements Serializable {

    @Positive(message = "{generic.positive}")
    private Long movieId;

    @Pattern(regexp = "^[a-zA-Z0-9-_]{5,255}$",message = "{saveUser.username.pattern}")
    private String username;

    @Min(value=0, message = "{generic.min}") @Max(value=5, message = "{generic.max}")
    private Integer rating;

    public Long getMovieId(){
        return movieId;
    }

    public void setMovieId(Long movieId){
        this.movieId = movieId;
    }

    public String getUsername(){
        return username;
    }

    public void setUsername(String username){
        this.username = username;
    }

    public Integer getRating(){
        return rating;
    }

    public void setRating(Integer rating){
        this.rating = rating;
    }
}
