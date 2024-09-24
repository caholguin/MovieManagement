package net.projects.MovieManagement.controller;

import jakarta.validation.Valid;
import net.projects.MovieManagement.dto.request.SaveUserDTO;
import net.projects.MovieManagement.dto.response.GetUserDTO;
import net.projects.MovieManagement.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public ResponseEntity<List<GetUserDTO>> getAllUsers(@RequestParam(required = false) String name) {

        List<GetUserDTO> users = null;

        if(StringUtils.hasText(name)) {
            users = userService.findAllByName(name);
        }else{
            users = userService.findAll();
        }

        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @GetMapping("/{username}")
    public ResponseEntity<GetUserDTO> findById(@PathVariable String username){
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
}
