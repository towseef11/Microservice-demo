package com.userservice.UserService.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.userservice.UserService.Model.LoginRequest;
import com.userservice.UserService.Model.LoginResponse;
import com.userservice.UserService.Model.UpdateUserRequest;
import com.userservice.UserService.Model.User;
import com.userservice.UserService.Service.UserService;

@RestController 
@RequestMapping ("/user")
public class UserController {

    @Autowired UserService userService;

    @PostMapping("/signup")
    public User addUser(@RequestBody User user){
        return userService.addUser(user);
    }

    @PatchMapping ("/Update")
    public User update(@RequestBody UpdateUserRequest updateUserRequest,Authentication authentication){
            String email = authentication.getName();
            return  userService.updateUser(email, updateUserRequest);
    }

    @PostMapping("/signin")
    public LoginResponse UserLogin(@RequestBody LoginRequest request){
        return  userService.userLogin(request.getEmail(), request.getPassword());
    }
}
