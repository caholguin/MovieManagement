package net.projects.MovieManagement.repository;

import net.projects.MovieManagement.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long>, JpaSpecificationExecutor<User> {

    Page<User> findByNameContaining(String name, Pageable pageable);

    Optional<User> findByUsername(String username);

    @Modifying
    int deleteByUsername(String username);

}
