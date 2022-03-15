package com.todo.service;

import com.todo.exception.UserNotFoundException;
import com.todo.model.User;

public interface UserService {

    boolean login(User user);
    boolean saveUser(User user);
    User findUserByUserIdAndPassword(String userId, String password) throws UserNotFoundException;
}
