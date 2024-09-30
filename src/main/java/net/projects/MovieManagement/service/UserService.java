package net.projects.MovieManagement.service;

import net.projects.MovieManagement.dto.request.SaveUserDTO;
import net.projects.MovieManagement.dto.response.GetUserDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UserService {

    Page<GetUserDTO> findAll(String name, Pageable pageable);

    GetUserDTO findOneByUsername(String username);

    GetUserDTO CreateOne(SaveUserDTO saveUserDTO);

    GetUserDTO updateOneByUsername(String username, SaveUserDTO saveUserDTO);

    void deleteOneByUsername(String username);
}
