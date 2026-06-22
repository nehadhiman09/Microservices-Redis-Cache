package com.example.user_service.Controller;

import com.example.user_service.DTO.UserDto;
import com.example.user_service.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService service;



    @GetMapping("/email/{email}")
    public Boolean exists(@PathVariable String email){
        return service.emailExists(email);
    }

    @GetMapping("/details/{email}")
    public UserDto details(@PathVariable String email){

        return service.getUserByEmail(email);

    }

}
