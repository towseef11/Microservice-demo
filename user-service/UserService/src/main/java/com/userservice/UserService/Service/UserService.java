package com.userservice.UserService.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.userservice.UserService.Model.LoginResponse;
import com.userservice.UserService.Model.UpdateUserRequest;
import com.userservice.UserService.Model.User;
import com.userservice.UserService.Repo.UserRepo;

@Service 
public class UserService {

    private final UserRepo userRepo;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public UserService(UserRepo userRepo, PasswordEncoder passwordEncoder, JwtService jwtService){
        this.userRepo = userRepo;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
    }

    public User addUser(User user){
        String hashedpassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(hashedpassword);
        return userRepo.save(user);
    }

    public LoginResponse  userLogin(String email , String password ){

        User user = userRepo.findByEmail(email).orElseThrow(() ->
                    new RuntimeException("Invalid email or password"));
        
        boolean passwordMatch = passwordEncoder.matches(password, user.getPassword());

        if(!passwordMatch){
            throw new RuntimeException("Invalid email or password");
        }
        String token = jwtService.generateToken(user);

        return new LoginResponse(token);

    }

    public User updateUser(String email, UpdateUserRequest updateUserRequest){
        User user = userRepo.findByEmail(email).orElseThrow(()-> new RuntimeException("User not found"));

        if(updateUserRequest.getName() != null){
            user.setName(updateUserRequest.getName());
        }

        if(updateUserRequest.getMobilenumber() != null){
            user.setMobilenumber(updateUserRequest.getMobilenumber());
        }

        if(updateUserRequest.getAddress() != null){
            user.setAddress(updateUserRequest.getAddress());
        }

        return userRepo.save(user);

    }


}
