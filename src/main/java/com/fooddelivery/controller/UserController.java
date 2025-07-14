package com.fooddelivery.controller;

import com.fooddelivery.entity.User;
import com.fooddelivery.repository.UserRepository;
import com.fooddelivery.response.ResponseStructure;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserRepository userRepo;

    @PostMapping
    public ResponseEntity<ResponseStructure<User>> createUser(@RequestBody User user) {
        User user2 =  userRepo.save(user);
        ResponseStructure<User> responseStructure = new ResponseStructure<User>();
        responseStructure.setMsg(user.getName() + " Register succesfully to DB");
        responseStructure.setResponseId(HttpStatus.CREATED.value());
        responseStructure.setData(user2);
        return new ResponseEntity<ResponseStructure<User>>(responseStructure , HttpStatus.CREATED);
    }

    @GetMapping
    public List<User> getAllUsers() {
        return userRepo.findAll();
    }
}
