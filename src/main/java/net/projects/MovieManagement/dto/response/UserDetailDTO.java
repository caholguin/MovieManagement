package net.projects.MovieManagement.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;

public class UserDetailDTO {

    private Long id;
    private String username;
    private LocalDateTime createdAt;
    private int totalRatings;
    private Double averageRating;
    private int lowestRating;
    private int highestRating;

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

    public LocalDateTime getCreatedAt(){
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt){
        this.createdAt = createdAt;
    }

    public int getTotalRatings(){
        return totalRatings;
    }

    public void setTotalRatings(int totalRatings){
        this.totalRatings = totalRatings;
    }

    public Double getAverageRating(){
        return averageRating;
    }

    public void setAverageRating(Double averageRating){
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
