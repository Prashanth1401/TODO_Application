package com.todo.controller;

import com.todo.exception.UserNotFoundException;
import com.todo.model.User;
import com.todo.repository.UserRepository;
import com.todo.service.SecurityTokenGenerator;
import com.todo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@CrossOrigin
@RequestMapping("/api/v2")
public class AuthController {

    @Autowired
    UserService service;
    @Autowired
    UserRepository userRepository;

    @Autowired
    SecurityTokenGenerator securityTokenGenerator;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody User user) throws UserNotFoundException {
        ResponseEntity<?> responseEntity;
        try {
            User newUser=service.findUserByUserIdAndPassword(user.getUserId(),user.getPassword());
            if(newUser.getUserId().equals(user.getUserId())){
                Map<String,String> tokenMap = securityTokenGenerator.generateToken(newUser);
                responseEntity=new ResponseEntity<>(tokenMap, HttpStatus.OK);
            }
            else {
                responseEntity=new ResponseEntity<>("Invalid User",HttpStatus.NOT_FOUND);
            }
        }
        catch (UserNotFoundException e){
            throw new UserNotFoundException();
        }
        catch (Exception e){
            responseEntity=new ResponseEntity<>("Other error occured",HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }

    @PostMapping("/save")

    public ResponseEntity<User> save(@RequestBody User user){
        service.saveUser(user);
        return new ResponseEntity<User>(user, HttpStatus.CREATED);
    }
}
