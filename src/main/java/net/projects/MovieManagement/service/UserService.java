package net.projects.MovieManagement.service;

import net.projects.MovieManagement.dto.request.SaveUserDTO;
import net.projects.MovieManagement.dto.response.GetUserDTO;
import net.projects.MovieManagement.entity.User;

import java.util.List;

public interface UserService {

    List<GetUserDTO> findAll();

    List<GetUserDTO> findAllByName(String name);

    GetUserDTO findOneByUsername(String username);

    GetUserDTO CreateOne(SaveUserDTO saveUserDTO);

    GetUserDTO updateOneByUsername(String username, SaveUserDTO saveUserDTO);

    void deleteOneByUsername(String username);

}
