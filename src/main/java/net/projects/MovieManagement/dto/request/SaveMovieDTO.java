package net.projects.MovieManagement.dto.request;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.*;
import net.projects.MovieManagement.util.MovieGenre;

import java.io.Serializable;


public class SaveMovieDTO implements Serializable {

    @NotBlank(message = "{generic.notblank}")
    @Size(min = 4, max = 255, message = "{generic.size}")
    private String title;

    @NotBlank(message = "{generic.notblank}")
    @Size(min = 4, max = 255 , message = "{generic.size}")
    private String director;

    private MovieGenre genre;

    @Min(value = 1900, message = "{generic.min}")
    @JsonProperty(value = "release_year")
    private int releaseYear;

   /* @JsonProperty("availability_end_time")
    @JsonFormat(pattern = "yyy-MM-dd")
    @PastOrPresent
    public LocalDate availabilityEndTime;*/

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
}
