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
import net.projects.MovieManagement.util.MovieGenre;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class MovieServiceImpl implements MovieService {

    @Autowired
    private MovieRespository movieRespository;

    @Override
    public List<GetMovieDTO> findAll(MovieSearchCriteriaDTO searchCriteria){

        FindAllMovieEspecification movieSpecification = new FindAllMovieEspecification(searchCriteria);

        List<Movie> movies = movieRespository.findAll(movieSpecification);
        return MovieMapper.toDtoList(movies);
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
