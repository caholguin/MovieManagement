package net.projects.MovieManagement.service.impl;

import net.projects.MovieManagement.dto.request.MovieSearchCriteriaDTO;
import net.projects.MovieManagement.dto.request.SaveMovieDTO;
import net.projects.MovieManagement.dto.response.GetMovieDTO;
import net.projects.MovieManagement.entity.Movie;
import net.projects.MovieManagement.exception.ObjectoNotFoundException;
import net.projects.MovieManagement.mapper.MovieMapper;
import net.projects.MovieManagement.repository.MovieRespository;
import net.projects.MovieManagement.repository.specification.FindAllMovieEspecification;
import net.projects.MovieManagement.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public class MovieServiceImpl implements MovieService {

    @Autowired
    private MovieRespository movieRespository;

    @Override
    public Page<GetMovieDTO> findAll(MovieSearchCriteriaDTO searchCriteria, Pageable pageable){

        FindAllMovieEspecification movieSpecification = new FindAllMovieEspecification(searchCriteria);

        Page<Movie> movies = movieRespository.findAll(movieSpecification,pageable);

        return movies.map(MovieMapper::toDto);

        //return MovieMapper.toDtoList(movies);
    }


    @Override
    public GetMovieDTO createOne(SaveMovieDTO saveMovieDTO){
        Movie newMovie = MovieMapper.toEntity(saveMovieDTO);
        return MovieMapper.toDto(movieRespository.save(newMovie));
    }

    @Override
    public GetMovieDTO findOneById(Long id){
        return MovieMapper.toDto(this.findOneByIdEntity(id));
    }

    private Movie findOneByIdEntity(Long id){
        return movieRespository.findById(id)
                .orElseThrow(() -> new ObjectoNotFoundException("movie: " + id + " not found"));
    }

    @Override
    public GetMovieDTO updateOneById(Long id, SaveMovieDTO saveMovieDTO){

        Movie oldMovie = this.findOneByIdEntity(id);
        MovieMapper.updateEntity(oldMovie, saveMovieDTO);

        return MovieMapper.toDto(movieRespository.save(oldMovie));
    }

    @Override
    public void deleteOneById(Long id){
        Movie movie = this.findOneByIdEntity(id);
        movieRespository.delete(movie);
    }
}
