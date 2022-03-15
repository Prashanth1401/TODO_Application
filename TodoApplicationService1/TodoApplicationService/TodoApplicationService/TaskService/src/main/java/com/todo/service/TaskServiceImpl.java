package com.todo.service;

import com.todo.config.Producer;
import com.todo.exception.TaskNotFoundException;
import com.todo.exception.UserNotFoundException;
import com.todo.model.Task;
import com.todo.model.User;
import com.todo.rabbitmq.domain.ImpServiceDTO;
import com.todo.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class TaskServiceImpl implements TaskService {

    @Autowired
    TaskRepository repository;

    @Autowired
    Producer producer;

    @Override
    public User register(User user) {
        repository.save(user);
        return user;
    }

    @Override
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

    @Override
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

    @Override
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

    @Override
    public List<Task> getAllTask(String userId) {
        List<Task> tasks = repository.findById(userId).get().getTask();

        return tasks;
    }

    @Override
    public boolean updateImpTask(String taskId, String userId) {
        ImpServiceDTO impServiceDTO = new ImpServiceDTO(userId,taskId);
        producer.sendMessageToRabbitTemplate(impServiceDTO);
        return true;
    }
}
