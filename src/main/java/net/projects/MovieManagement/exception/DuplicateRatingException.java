package net.projects.MovieManagement.exception;

public class DuplicateRatingException extends RuntimeException {

    private String username;
    private Long movieId;

    public DuplicateRatingException(String username, Long movieId){
        this.username = username;
        this.movieId = movieId;
    }

    @Override
    public String getMessage(){
        return String.format("Username %s already exists in Movie %d", this.username, this.movieId);
    }
}
