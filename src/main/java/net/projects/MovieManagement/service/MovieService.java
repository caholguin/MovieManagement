package net.projects.MovieManagement.service;

import net.projects.MovieManagement.dto.request.MovieSearchCriteriaDTO;
import net.projects.MovieManagement.dto.request.SaveMovieDTO;
import net.projects.MovieManagement.dto.response.GetMovieDTO;
import net.projects.MovieManagement.dto.response.MovieDetailDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


import java.util.List;

public interface MovieService {

    Page<GetMovieDTO> findAll(MovieSearchCriteriaDTO searchCriteria, Pageable pageable);

    MovieDetailDTO findOneById(Long id);

    GetMovieDTO createOne(SaveMovieDTO saveMovieDTO);

    GetMovieDTO updateOneById(Long id, SaveMovieDTO saveMovieDTO);

    void deleteOneById(Long id);

}
