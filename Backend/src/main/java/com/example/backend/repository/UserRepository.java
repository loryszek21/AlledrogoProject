package com.example.backend.repository;

import com.example.backend.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
Optional<User> findByUsername(String username);
User findByEmail(String email);
//Optional<User> findByUsernameForReview(String username);

}
