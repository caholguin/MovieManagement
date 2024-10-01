package net.projects.MovieManagement.service.impl;

import jakarta.persistence.EntityManager;
import net.projects.MovieManagement.dto.request.SaveRatingDTO;
import net.projects.MovieManagement.dto.response.GetRatingDTO;
import net.projects.MovieManagement.entity.Rating;
import net.projects.MovieManagement.entity.User;
import net.projects.MovieManagement.exception.DuplicateRatingException;
import net.projects.MovieManagement.exception.ObjectoNotFoundException;
import net.projects.MovieManagement.mapper.RatingMapper;
import net.projects.MovieManagement.repository.RatingRepository;
import net.projects.MovieManagement.service.RatingService;
import net.projects.MovieManagement.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RatingServiceImpl implements RatingService {

    @Autowired
    private RatingRepository ratingRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private EntityManager entityManager;

    @Override
    public Page<GetRatingDTO> findAll(Pageable pageable){
        return ratingRepository.findAll(pageable).map(RatingMapper::toDto);
    }

    @Override
    public Page<GetRatingDTO> findAllByMovieId(Long movieId,Pageable pageable){
        return ratingRepository.findByMovieId(movieId,pageable).map(RatingMapper::toDto);
    }

    @Override
    public Page<GetRatingDTO> findAllByUsername(String username,Pageable pageable){
        return ratingRepository.findByUserUsername(username,pageable).map(RatingMapper::toDto);
    }

    @Override
    public GetRatingDTO findById(Long id){
        return RatingMapper.toDto(this.getRating(id));
    }

    private Rating getRating(Long id){
        return ratingRepository.findById(id).orElseThrow(() -> new ObjectoNotFoundException("[rating: " + id + "Object not found]"));
    }

    @Override
    public GetRatingDTO create(SaveRatingDTO saveRatingDTO){

        boolean ratingExists = ratingRepository.existsByMovieIdAndUserUsername(saveRatingDTO.getMovieId(),saveRatingDTO.getUsername());
        if (ratingExists) {
            throw  new DuplicateRatingException(saveRatingDTO.getUsername(), saveRatingDTO.getMovieId());
        }

        User user = userService.findOneByUsernameEntity(saveRatingDTO.getUsername());

        Rating rating = RatingMapper.toEntity(saveRatingDTO, user.getId());

        ratingRepository.save(rating);
        entityManager.detach(rating);

        return ratingRepository.findById(rating.getId())
                .map(RatingMapper::toDto)
                .get();

    }

    @Override
    public GetRatingDTO update(Long id, SaveRatingDTO saveRatingDTO){

        Rating oldRating = this.getRating(id);
        User user = userService.findOneByUsernameEntity(saveRatingDTO.getUsername());
        RatingMapper.updateEntity(oldRating,saveRatingDTO,user.getId());

        return RatingMapper.toDto(ratingRepository.save(oldRating));
    }

    @Override
    public void delete(Long id){

        if (ratingRepository.existsById(id)) {
            ratingRepository.deleteById(id);
            return;
        }

        throw new ObjectoNotFoundException("[rating: " + id + "Object not found]");
    }
}
