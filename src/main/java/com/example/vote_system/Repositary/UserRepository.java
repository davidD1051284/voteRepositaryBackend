package com.example.vote_system.Repositary;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.vote_system.Entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
}
