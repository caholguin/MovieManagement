package net.projects.MovieManagement.controller;

import jakarta.validation.Valid;
import net.projects.MovieManagement.dto.request.SaveMovieDTO;
import net.projects.MovieManagement.dto.response.GetMovieDTO;
import net.projects.MovieManagement.exception.ObjectoNotFoundException;
import net.projects.MovieManagement.service.MovieService;
import net.projects.MovieManagement.util.MovieGenre;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/movies")
public class MovieController {

    @Autowired
    private MovieService movieService;

    @GetMapping
    public ResponseEntity<List<GetMovieDTO>> findAll(@RequestParam(required = false) String title, @RequestParam(required = false) MovieGenre genre){

        List<GetMovieDTO> movies = null;

        if(StringUtils.hasText(title) && genre != null){
            movies = movieService.findAllByGenreAndTitle(genre, title);
        }else if(StringUtils.hasText(title)){
            movies = movieService.findAllByTitle(title);
        }else if(genre != null){
            movies = movieService.findAllByGenre(genre);
        }else {
            movies = movieService.findAll();
        }

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