package net.projects.MovieManagement.repository;

import jakarta.transaction.Transactional;
import net.projects.MovieManagement.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    List<User> findByNameContaining(String name);

    Optional<User> findByUsername(String username);

    @Modifying
    int deleteByUsername(String username);

}
