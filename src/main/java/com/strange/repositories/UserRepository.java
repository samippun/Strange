package com.strange.repositories;

import com.strange.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {

    Optional<User> findByUsername(String username);

    @Query("SELECT u FROM User u WHERE u.userRole = 'ROLE_USER'")
    List<User> findAllUsersByUserRole();

    @Query("SELECT COUNT(u) FROM User u WHERE u.userRole = 'ROLE_USER'")
    long countUsersByRole();
}
