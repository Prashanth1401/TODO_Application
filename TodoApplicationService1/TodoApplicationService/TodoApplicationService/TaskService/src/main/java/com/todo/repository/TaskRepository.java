package com.todo.repository;

import com.todo.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface TaskRepository extends MongoRepository<User,String> {
}
