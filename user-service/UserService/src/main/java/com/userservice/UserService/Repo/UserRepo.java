package com.userservice.UserService.Repo;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

import com.userservice.UserService.Model.User;

public interface UserRepo extends JpaRepository<User, Long> {

    public Optional<User> findByEmail(String email);
    
}
