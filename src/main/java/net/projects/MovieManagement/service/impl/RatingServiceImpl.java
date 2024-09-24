package net.projects.MovieManagement.service.impl;

import net.projects.MovieManagement.entity.Rating;
import net.projects.MovieManagement.exception.ObjectoNotFoundException;
import net.projects.MovieManagement.repository.RatingRepository;
import net.projects.MovieManagement.service.RatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RatingServiceImpl implements RatingService {

    @Autowired
    private RatingRepository ratingRepository;

    @Override
    public List<Rating> getAll(){
        return ratingRepository.findAll();
    }

    @Override
    public List<Rating> findAllByMovieId(Long movieId){
        return ratingRepository.findByMovieId(movieId);
    }

    @Override
    public List<Rating> findAllByUsername(String username){
        return ratingRepository.findByUserUsername(username);
    }

    @Override
    public Rating getById(Long id){
        return ratingRepository.findById(id).orElseThrow(() -> new ObjectoNotFoundException("[rating: " + id + "Object not found]"));
    }

    @Override
    public Rating createOne(Rating rating){
        return ratingRepository.save(rating);
    }

    @Override
    public Rating updateOneById(Long id, Rating rating){
        Rating oldRating = this.getById(id);

        oldRating.setUserId(rating.getUserId());
        oldRating.setMovieId(rating.getMovieId());
        oldRating.setRating(rating.getRating());

        return ratingRepository.save(oldRating);
    }

    @Override
    public void deleteOneById(Long id){

        if (ratingRepository.existsById(id)) {
            ratingRepository.deleteById(id);
            return;
        }

        throw new ObjectoNotFoundException("[rating: " + id + "Object not found]");
    }
}
