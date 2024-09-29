package net.projects.MovieManagement.controller;

import jakarta.validation.Valid;
import net.projects.MovieManagement.dto.request.MovieSearchCriteriaDTO;
import net.projects.MovieManagement.dto.request.SaveMovieDTO;
import net.projects.MovieManagement.dto.response.GetMovieDTO;
import net.projects.MovieManagement.service.MovieService;
import net.projects.MovieManagement.util.MovieGenre;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/movies")
public class MovieController {

    @Autowired
    private MovieService movieService;

    @GetMapping
    public ResponseEntity<List<GetMovieDTO>> findAll(@RequestParam(required = false) String title,
                                                     @RequestParam(required = false) MovieGenre genre,
                                                     @RequestParam(required = false, name = "min_release_year") Integer minReleaseYear,
                                                     @RequestParam(required = false, name = "max_release_year") Integer maxReleaseYear,
                                                     @RequestParam(required = false, name = "min_average_rating") Integer minAverageRating){

        MovieSearchCriteriaDTO searchCriteriaDTO = new MovieSearchCriteriaDTO(title,genre,minReleaseYear,maxReleaseYear,minAverageRating);

        List<GetMovieDTO> movies = movieService.findAll(searchCriteriaDTO);

        return new ResponseEntity<>(movies, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<GetMovieDTO> findById(@PathVariable Long id){
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

}