package net.projects.MovieManagement.service.impl;

import jakarta.persistence.criteria.Predicate;
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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public Page<GetUserDTO> findAll(String name, Pageable pageable){

        Specification<User> userSpecification = (root, query, builder) -> {
            if (StringUtils.hasText(name)){
                Predicate nameLike = builder.like(root.get("name"), "%" + name + "%");
                return nameLike;
            }
            return null;
        };

        Page<User> users = userRepository.findAll(userSpecification,pageable);
        return users.map(UserMapper::toDto);
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
