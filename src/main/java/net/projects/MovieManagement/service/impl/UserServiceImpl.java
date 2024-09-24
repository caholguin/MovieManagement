package net.projects.MovieManagement.service.impl;

import jakarta.transaction.Transactional;
import net.projects.MovieManagement.dto.request.SaveUserDTO;
import net.projects.MovieManagement.dto.response.GetUserDTO;
import net.projects.MovieManagement.entity.User;
import net.projects.MovieManagement.exception.ObjectoNotFoundException;
import net.projects.MovieManagement.mapper.UserMapper;
import net.projects.MovieManagement.repository.UserRepository;
import net.projects.MovieManagement.service.UserService;
import net.projects.MovieManagement.service.validator.PasswordValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<GetUserDTO> findAll(){
        List<User> users = userRepository.findAll();
        return UserMapper.toDtoList(users);
    }

    @Override
    public List<GetUserDTO> findAllByName(String name){
        List<User> users = userRepository.findByNameContaining(name);
        return UserMapper.toDtoList(users);
    }

    @Override
    public GetUserDTO findOneByUsername(String username){
        return UserMapper.toDto(this.findOneByUsernameEntity(username));
    }

    private User findOneByUsernameEntity(String username){
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new ObjectoNotFoundException("[user: " + username + "]"));
    }

    @Override
    public GetUserDTO CreateOne(SaveUserDTO saveUserDTO){

        PasswordValidator.validatePassword(saveUserDTO.getPassword(),saveUserDTO.getPasswordRepeated());

        User newUser = UserMapper.toEntity(saveUserDTO);
        return UserMapper.toDto(userRepository.save(newUser));
    }

    @Override
    public GetUserDTO updateOneByUsername(String username, SaveUserDTO saveUserDTO){

        PasswordValidator.validatePassword(saveUserDTO.getPassword(),saveUserDTO.getPasswordRepeated());

        User oldUser = this.findOneByUsernameEntity(username);

        UserMapper.updateEntity(oldUser,saveUserDTO);

        return UserMapper.toDto(userRepository.save(oldUser));
    }

    @Override
    @Transactional
    public void deleteOneByUsername(String username){

        if (userRepository.deleteByUsername(username) != 1) {
            throw new ObjectoNotFoundException("[user: " +username + "] not found");
        }
    }
}
