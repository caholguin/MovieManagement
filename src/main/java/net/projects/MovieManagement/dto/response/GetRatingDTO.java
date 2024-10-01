package net.projects.MovieManagement.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

public class GetRatingDTO implements Serializable {

    private Long id;
    private Long movieId;
    private String movieTitle;
    private Long userId;
    private String username;
    private int rating;

    public Long getId(){
        return id;
    }

    public void setId(Long id){
        this.id = id;
    }

    public Long getMovieId(){
        return movieId;
    }

    public void setMovieId(Long movieId){
        this.movieId = movieId;
    }

    public String getMovieTitle(){
        return movieTitle;
    }

    public void setMovieTitle(String movieTitle){
        this.movieTitle = movieTitle;
    }

    public Long getUserId(){
        return userId;
    }

    public void setUserId(Long userId){
        this.userId = userId;
    }

    public String getUsername(){
        return username;
    }

    public void setUsername(String username){
        this.username = username;
    }

    public int getRating(){
        return rating;
    }

    public void setRating(int rating){
        this.rating = rating;
    }
}
