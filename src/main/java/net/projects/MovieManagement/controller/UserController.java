package net.projects.MovieManagement.controller;

import jakarta.validation.Valid;
import net.projects.MovieManagement.dto.request.SaveUserDTO;
import net.projects.MovieManagement.dto.response.GetUserDTO;
import net.projects.MovieManagement.dto.response.UserDetailDTO;
import net.projects.MovieManagement.service.RatingService;
import net.projects.MovieManagement.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private RatingService ratingService;

    @GetMapping
    public ResponseEntity<Page<GetUserDTO>> getAll(@RequestParam(required = false) String name, Pageable pageable){

        Page<GetUserDTO> users = userService.findAll(name, pageable);

        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @GetMapping("/{username}")
    public ResponseEntity<UserDetailDTO> findById(@PathVariable String username){
        return new ResponseEntity<>(userService.findOneByUsername(username), HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<GetUserDTO> create(@RequestBody @Valid SaveUserDTO saveUserDTO){

        GetUserDTO userCreate = userService.CreateOne(saveUserDTO);

        return new ResponseEntity<>(userCreate, HttpStatus.CREATED);

    }

    @PutMapping("/{username}")
    public ResponseEntity<GetUserDTO> update(@PathVariable String username, @Valid @RequestBody SaveUserDTO saveUserDTO){
        GetUserDTO userUpdate = userService.updateOneByUsername(username, saveUserDTO);
        return new ResponseEntity<>(userUpdate, HttpStatus.OK);
    }

    @DeleteMapping("/{username}")
    public ResponseEntity<Void> delete(@PathVariable String username){
        userService.deleteOneByUsername(username);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/{username}/ratings")
    public ResponseEntity<Page<GetUserDTO.GetRatingDTO>> getRatingsByUsername(@PathVariable String username, Pageable pageable){
        return new ResponseEntity<>(ratingService.findAllByUsername(username,pageable),HttpStatus.OK);
    }
}
