package net.projects.MovieManagement.service;

import net.projects.MovieManagement.dto.request.SaveRatingDTO;
import net.projects.MovieManagement.dto.response.GetMovieDTO;
import net.projects.MovieManagement.dto.response.GetRatingDTO;
import net.projects.MovieManagement.dto.response.GetUserDTO;
import net.projects.MovieManagement.entity.Rating;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface RatingService {

    Page<GetRatingDTO> findAll(Pageable pageable);

    Page<GetMovieDTO.GetRatingDTO> findAllByMovieId(Long movieId, Pageable pageable);

    Page<GetUserDTO.GetRatingDTO> findAllByUsername(String username, Pageable pageable);

    GetRatingDTO findById(Long id);

    GetRatingDTO create(SaveRatingDTO saveRatingDTO);

    GetRatingDTO update(Long id, SaveRatingDTO saveRatingDTO);

    void delete(Long id);

}
