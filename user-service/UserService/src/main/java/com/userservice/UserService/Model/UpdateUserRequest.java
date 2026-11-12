package com.userservice.UserService.Model;

import lombok.Data;

@Data 
public class UpdateUserRequest {

    private String name;
    private String mobilenumber;
    private String address;
    
}
