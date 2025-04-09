package com.connectfour.Repository;

// Includes Optional class
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.connectfour.Entity.User;

// Creates repo to interact with users table in the database
@Repository
public interface UserRepository extends JpaRepository<User, String>{
    Optional<User> findByUsername(String username);
}
