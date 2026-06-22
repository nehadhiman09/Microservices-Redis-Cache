package com.example.user_service.service;

import com.example.user_service.DTO.UserDto;
import com.example.user_service.Entity.User;
import com.example.user_service.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepository repository;

    public Boolean emailExists(String email){

        return repository.existsByEmail(email);

    }

    public UserDto getUserByEmail(String email){

        User user =
                repository.findByEmail(email)
                        .orElseThrow();

        UserDto dto = new UserDto();

        dto.setId(user.getId());

        dto.setEmail(user.getEmail());

        dto.setRole(user.getRole());

        return dto;

    }


}
