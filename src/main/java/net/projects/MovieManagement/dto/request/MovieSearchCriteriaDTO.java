package net.projects.MovieManagement.dto.request;

import net.projects.MovieManagement.util.MovieGenre;

import java.io.Serializable;

public class MovieSearchCriteriaDTO implements Serializable {

    private String title;

    private MovieGenre genre;

    private Integer minReleaseYear;

    private Integer maxReleaseYear;

    private Integer minAverageRating;


    public MovieSearchCriteriaDTO(String title, MovieGenre genre, Integer minReleaseYear, Integer maxReleaseYear, Integer minAverageRating){
        this.title = title;
        this.genre = genre;
        this.minReleaseYear = minReleaseYear;
        this.maxReleaseYear = maxReleaseYear;
        this.minAverageRating = minAverageRating;
    }

    public String getTitle(){
        return title;
    }

    public MovieGenre getGenre(){
        return genre;
    }

    public Integer getMinReleaseYear(){
        return minReleaseYear;
    }

    public Integer getMaxReleaseYear(){
        return maxReleaseYear;
    }

    public Integer getMinAverageRating(){
        return minAverageRating;
    }
}
