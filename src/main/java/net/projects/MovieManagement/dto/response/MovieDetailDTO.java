package net.projects.MovieManagement.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import net.projects.MovieManagement.util.MovieGenre;

import java.io.Serializable;

public class MovieDetailDTO implements Serializable {

    private Long id;
    private String title;
    private String director;
    private MovieGenre genre;
    private int totalRatings;
    private int releaseYear;
    @JsonFormat(pattern = "0.00")
    private double averageRating;
    private int lowestRating;
    private int highestRating;

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

    public int getTotalRatings(){
        return totalRatings;
    }

    public void setTotalRatings(int totalRatings){
        this.totalRatings = totalRatings;
    }

    public int getReleaseYear(){
        return releaseYear;
    }

    public void setReleaseYear(int releaseYear){
        this.releaseYear = releaseYear;
    }

    public double getAverageRating(){
        return averageRating;
    }

    public void setAverageRating(double averageRating){
        this.averageRating = averageRating;
    }

    public int getLowestRating(){
        return lowestRating;
    }

    public void setLowestRating(int lowestRating){
        this.lowestRating = lowestRating;
    }

    public int getHighestRating(){
        return highestRating;
    }

    public void setHighestRating(int highestRating){
        this.highestRating = highestRating;
    }
}
