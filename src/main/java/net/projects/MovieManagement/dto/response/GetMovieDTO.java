package net.projects.MovieManagement.dto.response;


import com.fasterxml.jackson.annotation.JsonProperty;
import net.projects.MovieManagement.util.MovieGenre;

import java.io.Serializable;
import java.util.List;

public class GetMovieDTO implements Serializable {

    private Long id;
    private String title;
    private String director;
    private MovieGenre genre;
    @JsonProperty(value="release_year")
    int releaseYear;
    int totalRatings;

    public Long getId(){
        return id;
    }

    public void setId(Long id){
        this.id = id;
    }

    public String getTitle(){
        return title;
    }

    public void setTitle(String title){
        this.title = title;
    }

    public String getDirector(){
        return director;
    }

    public void setDirector(String director){
        this.director = director;
    }

    public MovieGenre getGenre(){
        return genre;
    }

    public void setGenre(MovieGenre genre){
        this.genre = genre;
    }

    public int getReleaseYear(){
        return releaseYear;
    }

    public void setReleaseYear(int releaseYear){
        this.releaseYear = releaseYear;
    }

    public int getTotalRatings(){
        return totalRatings;
    }

    public void setTotalRatings(int totalRatings){
        this.totalRatings = totalRatings;
    }

    public static class GetRatingDTO implements Serializable {

        private Long id;
        private int rating;
        private String username;

        public GetRatingDTO(Long id, int rating, String username){
            this.id = id;
            this.rating = rating;
            this.username = username;
        }


        public Long getId(){
            return id;
        }

        public void setId(Long id){
            this.id = id;
        }

        public int getRating(){
            return rating;
        }

        public void setRating(int rating){
            this.rating = rating;
        }

        public String getUsername(){
            return username;
        }

        public void setUsername(String username){
            this.username = username;
        }
    }

}

