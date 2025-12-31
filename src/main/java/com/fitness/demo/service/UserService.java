package com.fitness.demo.service;

import com.fitness.demo.dto.RegisterRequest;
import com.fitness.demo.dto.UserResponse;
import com.fitness.demo.models.User;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.UUID;

@Service
@AllArgsConstructor
public class UserService {
    private UserRepository repository;
    public UserResponse register (RegisterRequest request){

        if(repository.existsByEmail(request.getEmail())){
            throw new RuntimeException("Email already exist");
        }
        User user =  new User();
        user.setEmail(request.getEmail());
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setPassword(request.getPassword());

        User savedUser = repository.save(user);
    UserResponse response = new  UserResponse();
    response.setId(savedUser.getId());
        response.setEmail(savedUser.getEmail());
        response.setFirstName(savedUser.getFirstName());
        response.setLastName(savedUser.getLastName());
        response.setCreatedAt(savedUser.getCreatedAt());
        response.setUpdatedAt(savedUser.getUpdatedAt());

        return response;
    }

    public UserResponse getUserProfile(UUID userId) {

        String id = userId.toString();
        User user = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + id));

        UserResponse userResponse = new UserResponse();
        userResponse.setId(user.getId());
        userResponse.setEmail(user.getEmail());
        userResponse.setFirstName(user.getFirstName());
        userResponse.setLastName(user.getLastName());
        userResponse.setCreatedAt(user.getCreatedAt());
        userResponse.setUpdatedAt(user.getUpdatedAt());


        return userResponse;
    }

}
