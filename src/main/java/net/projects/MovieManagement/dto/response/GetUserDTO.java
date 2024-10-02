package net.projects.MovieManagement.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

public class GetUserDTO implements Serializable {

    private Long id;
    private String username;
    private String name;
    private int totalRating;

    public Long getId(){
        return id;
    }

    public void setId(Long id){
        this.id = id;
    }

    public String getUsername(){
        return username;
    }

    public void setUsername(String username){
        this.username = username;
    }

    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name;
    }

    public int getTotalRating(){
        return totalRating;
    }

    public void setTotalRating(int totalRating){
        this.totalRating = totalRating;
    }

    public static class GetRatingDTO implements Serializable {

        private Long id;
        @JsonProperty(value="movie_title")
        private String movieTitle;
        @JsonProperty(value="movie_id")
        private Long movieId;
        private int rating;

        public GetRatingDTO(Long id, String movieTitle, Long movieId, int rating){
            this.id = id;
            this.movieTitle = movieTitle;
            this.movieId = movieId;
            this.rating = rating;
        }

        public Long getId(){
            return id;
        }

        public void setId(Long id){
            this.id = id;
        }

        public String getMovieTitle(){
            return movieTitle;
        }

        public void setMovieTitle(String movieTitle){
            this.movieTitle = movieTitle;
        }

        public Long getMovieId(){
            return movieId;
        }

        public void setMovieId(Long movieId){
            this.movieId = movieId;
        }

        public int getRating(){
            return rating;
        }

        public void setRating(int rating){
            this.rating = rating;
        }
    }
}
