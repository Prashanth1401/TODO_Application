package com.todo.service;

import com.todo.exception.ImpNotFoundException;
import com.todo.model.Task;
import com.todo.model.User;
import com.todo.repository.ImpRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ImpServiceImpl implements ImpService{



    @Autowired
   private ImpRepository impRepository;

    @Autowired
    public ImpServiceImpl(ImpRepository impRepository) {
        this.impRepository = impRepository;
    }

    public ImpServiceImpl() {

    }

    @Override
    public boolean addTask(Task task) {
        return true;
    }

    @Override
    public boolean addTask(Task task, String userId) throws ImpNotFoundException {
        if(impRepository.findById(userId).isEmpty()){
            throw new ImpNotFoundException();
        }
        User user = impRepository.findById(userId).get();
        List<Task> tasks = user.getTask();
        if(tasks == null) {
            tasks = new ArrayList<>();
        }
        tasks.add(task);
        user.setTask(tasks);
        impRepository.save(user);
        return true;
    }

    @Override
    public boolean deleteTask(String userId, String taskId) throws ImpNotFoundException {
        User user = impRepository.findById(userId).get();
        List<Task> tasks = user.getTask();
        Task task = tasks.stream()
                .filter(obj -> taskId.equals(obj.getTaskId()))
                .findAny().orElse(null);
        if(tasks == null || !tasks.contains(task)){
            throw new ImpNotFoundException();
        }
        tasks.remove(task);
        user.setTask(tasks);
        impRepository.save(user);
        return true;
    }

    @Override
    public boolean updateTask(Task task, String userId) throws ImpNotFoundException {
        User user = impRepository.findById(userId).get();
        List<Task> tasks = user.getTask();
        Task taskObj = tasks.stream()
                .filter(obj -> task.getTaskId().equals(obj.getTaskId()))
                .findAny().orElse(null);
        if(tasks == null){
            throw new ImpNotFoundException();
        }
        tasks.remove(taskObj);
        tasks.add(task);
        user.setTask(tasks);
        impRepository.save(user);
        return true;
    }

    @Override
    public List<Task> getAllTask(String userId) {
        List<Task> tasks = impRepository.findById(userId).get().getTask();

        return tasks;
    }

    @Override
    public boolean updateImpTask(String userId, String taskId) {
        User user = impRepository.findById(userId).get();
        List<Task> tasks = user.getTask();
        Task task = tasks.stream().filter(obj -> taskId.equals(obj.getTaskId())).findAny().orElse(null);
        tasks.remove(task);
        task.setIsImportant(true);
        tasks.add(task);
        user.setTask(tasks);
        impRepository.save(user);
        return true;
    }
}
