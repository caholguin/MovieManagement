package net.projects.MovieManagement.controller;

import jakarta.validation.Valid;
import net.projects.MovieManagement.dto.request.MovieSearchCriteriaDTO;
import net.projects.MovieManagement.dto.request.SaveMovieDTO;
import net.projects.MovieManagement.dto.response.GetMovieDTO;
import net.projects.MovieManagement.dto.response.MovieDetailDTO;
import net.projects.MovieManagement.service.MovieService;
import net.projects.MovieManagement.service.RatingService;
import net.projects.MovieManagement.util.MovieGenre;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/movies")
public class MovieController {

    @Autowired
    private MovieService movieService;

    @Autowired
    private RatingService ratingService;

    @GetMapping
    public ResponseEntity<Page<GetMovieDTO>> findAll(@RequestParam(required = false) String title,
                                                     @RequestParam(required = false) MovieGenre genre,
                                                     @RequestParam(required = false, name = "min_release_year") Integer minReleaseYear,
                                                     @RequestParam(required = false, name = "max_release_year") Integer maxReleaseYear,
                                                     @RequestParam(required = false, name = "min_average_rating") Integer minAverageRating,
                                                     Pageable pageable){

        MovieSearchCriteriaDTO searchCriteriaDTO = new MovieSearchCriteriaDTO(title,genre,minReleaseYear,maxReleaseYear,minAverageRating);

        Page<GetMovieDTO> movies = movieService.findAll(searchCriteriaDTO, pageable);

        return new ResponseEntity<>(movies, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MovieDetailDTO> findById(@PathVariable Long id){
        return new ResponseEntity<>(movieService.findOneById(id),HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<GetMovieDTO> create(@RequestBody @Valid SaveMovieDTO saveMovieDTO){
        GetMovieDTO movieCreated = movieService.createOne(saveMovieDTO);
        return new ResponseEntity<>(movieCreated,HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<GetMovieDTO> update(@PathVariable Long id, @Valid @RequestBody SaveMovieDTO saveMovieDTO){
        GetMovieDTO movieUpdate = movieService.updateOneById(id,saveMovieDTO);
        return new ResponseEntity<>(movieUpdate,HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        movieService.deleteOneById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/{id}/ratings")
    public ResponseEntity<Page<GetMovieDTO.GetRatingDTO>> findAllRatingForMovie(@PathVariable Long id, Pageable pageable){
        return new ResponseEntity<>(ratingService.findAllByMovieId(id,pageable),HttpStatus.OK);
    }

}