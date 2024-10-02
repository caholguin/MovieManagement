package net.projects.MovieManagement.mapper;

import net.projects.MovieManagement.dto.request.SaveUserDTO;
import net.projects.MovieManagement.dto.response.GetUserDTO;
import net.projects.MovieManagement.dto.response.UserDetailDTO;
import net.projects.MovieManagement.entity.User;

import java.util.List;

public class UserMapper {

    public static GetUserDTO toDto(User user){

        if (user == null) return null;

        int totalRating = user.getRatings() != null ? user.getRatings().size() : 0;

        GetUserDTO userDto = new GetUserDTO();
        userDto.setId(user.getId());
        userDto.setUsername(user.getUsername());
        userDto.setName(user.getName());
        userDto.setTotalRating(totalRating);
        return userDto;

    }

    public static List<GetUserDTO> toDtoList(List<User> users){

        if (users == null) return null;

        return users.stream()
                .map(UserMapper::toDto)
                .toList();
    }

    public static User toEntity(SaveUserDTO saveUserDTO){

        if (saveUserDTO == null) return null;

        User user = new User();
        user.setUsername(saveUserDTO.getUsername());
        user.setName(saveUserDTO.getName());
        user.setPassword(saveUserDTO.getPassword());

        return user;

    }

    public static void updateEntity(User oldUser, SaveUserDTO saveUserDTO){

        if (oldUser == null || saveUserDTO == null) return;

        oldUser.setName(saveUserDTO.getName());
        oldUser.setPassword(saveUserDTO.getPassword());
    }

    public static UserDetailDTO toDetailDto(User user, int totalRatings, double averageRatings, int lowestRating, int highestRating){

        if (user == null) return null;

        UserDetailDTO userDetailDTO = new UserDetailDTO();
        userDetailDTO.setId(user.getId());
        userDetailDTO.setUsername(user.getUsername());
        userDetailDTO.setCreatedAt(user.getCreatedAt());
        userDetailDTO.setTotalRatings(totalRatings);
        userDetailDTO.setAverageRating(averageRatings);
        userDetailDTO.setLowestRating(lowestRating);
        userDetailDTO.setHighestRating(highestRating);

        return userDetailDTO;
    }
}
