package com.todo.service;

import com.todo.exception.UserNotFoundException;
import com.todo.model.User;
import com.todo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    UserRepository repository;

    @Override
    public boolean login(User user) {
        return true;
    }

    @Override
    public boolean saveUser(User user) {
        repository.save(user);
        return true;
    }

    @Override
    public User findUserByUserIdAndPassword(String userId, String password) throws UserNotFoundException {
        User user= repository.findUserByUserIdAndPassword(userId,password);
        if(user == null){
            throw new UserNotFoundException();
        }
        return user;
    }
}
