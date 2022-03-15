package com.todo.service;

import com.todo.exception.ImpNotFoundException;
import com.todo.model.Task;

import java.util.List;

public interface ImpService {

    boolean updateImpTask(String userId, String taskId);
    public boolean addTask(Task task);
    public boolean addTask(Task task, String userId) throws ImpNotFoundException;
    public boolean deleteTask(String userId, String taskId) throws ImpNotFoundException;
    public boolean updateTask(Task task, String userId) throws ImpNotFoundException;
    public List<Task> getAllTask(String userId);
}
