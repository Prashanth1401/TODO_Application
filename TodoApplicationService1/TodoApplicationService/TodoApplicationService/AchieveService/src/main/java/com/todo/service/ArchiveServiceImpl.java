package com.todo.service;

import com.todo.exception.TaskNotFoundException;
import com.todo.exception.UserNotFoundException;
import com.todo.model.Task;
import com.todo.model.User;
import com.todo.repository.ArchiveRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ArchiveServiceImpl {

    @Autowired
    ArchiveRepository repository;

    public User searchTaskById(String userId) throws TaskNotFoundException {
        if(repository.findById(userId).isEmpty()){
            throw new TaskNotFoundException();
        }
        return repository.findById(userId).get();
    }


    public boolean addTask(Task task, String userId) throws UserNotFoundException {
        if(repository.findById(userId).isEmpty()){
            throw new UserNotFoundException();
        }
        User user = repository.findById(userId).get();
        List<Task> tasks = user.getTask();
        if(tasks == null) {
            tasks = new ArrayList<>();
        }
        tasks.add(task);
        user.setTask(tasks);
        repository.save(user);
        return true;
    }


    public boolean deleteTask(String userId, String taskId) throws TaskNotFoundException {
        User user = repository.findById(userId).get();
        List<Task> tasks = user.getTask();
        Task task = tasks.stream()
                .filter(obj -> taskId.equals(obj.getTaskId()))
                .findAny().orElse(null);
        if(tasks == null || !tasks.contains(task)){
            throw new TaskNotFoundException();
        }
        tasks.remove(task);
        user.setTask(tasks);
        repository.save(user);
        return true;
    }

    public boolean updateTask(Task task, String userId) throws TaskNotFoundException {
        User user = repository.findById(userId).get();
        List<Task> tasks = user.getTask();
        Task taskObj = tasks.stream()
                .filter(obj -> task.getTaskId().equals(obj.getTaskId()))
                .findAny().orElse(null);
        if(tasks == null){
            throw new TaskNotFoundException();
        }
        tasks.remove(taskObj);
        tasks.add(task);
        user.setTask(tasks);
        repository.save(user);
        return true;
    }

    public List<Task> getAllTask(String userId) {
        List<Task> tasks = repository.findById(userId).get().getTask();

        return tasks;
    }

    public boolean updateImpTask(String userId, String taskId) {
        User user = repository.findById(userId).get();
        List<Task> tasks = user.getTask();
        Task task = tasks.stream().filter(obj -> taskId.equals(obj.getTaskId())).findAny().orElse(null);
        tasks.remove(task);
        task.setImportant(true);
        tasks.add(task);
        user.setTask(tasks);
        repository.save(user);
        return true;
    }
}
