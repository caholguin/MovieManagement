package net.projects.MovieManagement.controller;

import jakarta.validation.Valid;
import net.projects.MovieManagement.dto.request.SaveRatingDTO;
import net.projects.MovieManagement.dto.response.GetRatingDTO;
import net.projects.MovieManagement.service.RatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/ratings")
public class RatingController {

    @Autowired
    private RatingService ratingService;

    @GetMapping
    public ResponseEntity<Page<GetRatingDTO>> getAll(Pageable pageable) {
        return new ResponseEntity<>(ratingService.findAll(pageable), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<GetRatingDTO> getById(@PathVariable Long id) {
        return new ResponseEntity<>(ratingService.findById(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<GetRatingDTO> create(@RequestBody @Valid SaveRatingDTO saveRatingDTO){
        GetRatingDTO getRatingDTO = ratingService.create(saveRatingDTO);
        return new ResponseEntity<>(getRatingDTO, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<GetRatingDTO> update(@PathVariable Long id, @RequestBody @Valid SaveRatingDTO saveRatingDTO){
        return new ResponseEntity<>(ratingService.update(id, saveRatingDTO), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        ratingService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
