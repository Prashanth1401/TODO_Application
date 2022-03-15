package com.todo.controller;


import com.todo.exception.ImpAlreadyExistException;

import com.todo.exception.ImpNotFoundException;

import com.todo.model.Task;
import com.todo.service.ImpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/imp")
public class ImpController {
    private ResponseEntity responseEntity;

    private ImpService impService;

    @Autowired
    public ImpController(ImpService impService) {
        this.impService = impService;
    }

    @PutMapping("/update/{userId}")
    public ResponseEntity<?> updateTask(@RequestBody Task task, @PathVariable String userId) throws ImpNotFoundException {
        if(impService.updateTask(task,userId)) {
            return new ResponseEntity<>("task updated",HttpStatus.OK);
        }
        else return new ResponseEntity<>("task not updated",HttpStatus.NOT_MODIFIED);
    }

    @GetMapping("/tasks/{userId}")
    public ResponseEntity<?> getAllTasks(@PathVariable String userId){
        return new ResponseEntity<>(impService.getAllTask(userId),HttpStatus.OK);
    }

    @PutMapping("/add/{userId}")
    public ResponseEntity<?> addTask(@RequestBody Task task, @PathVariable String userId) throws ImpNotFoundException {
        impService.addTask(task,userId);
        return new ResponseEntity<>("Task added",HttpStatus.OK);
    }

    @DeleteMapping("/delete/{userId}/{taskId}")
    public ResponseEntity<?> deleteTask(@PathVariable("userId") String userId, @PathVariable("taskId") String taskId) throws ImpNotFoundException {
        impService.deleteTask(userId,taskId);
        return new ResponseEntity<>("Task deleted",HttpStatus.OK);
    }
}
