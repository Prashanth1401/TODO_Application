package com.todo.repository;

import com.todo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User,String> {

    User findUserByUserIdAndPassword(String userId, String password);
}
