package com.connectfour.connectfour.Repository;

// Includes Optional class
import java.util.Optional;
// Includes super
import org.springframework.data.jpa.repository.JpaRepository;
// Includes Repository annotation
import org.springframework.stereotype.Repository;
// Custom class
import com.connectfour.connectfour.Entity.User;

// Creates repo to interact with users table in the database
@Repository
public interface UserRepository extends JpaRepository<User, String>{
    Optional<User> findByUsername(String username);
}