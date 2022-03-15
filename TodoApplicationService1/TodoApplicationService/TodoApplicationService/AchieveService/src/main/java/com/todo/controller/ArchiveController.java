package com.todo.controller;

import com.todo.exception.TaskNotFoundException;
import com.todo.exception.UserNotFoundException;
import com.todo.model.Task;
import com.todo.model.User;
import com.todo.service.ArchiveServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
public class ArchiveController {

    @Autowired
    ArchiveServiceImpl taskService;

    @GetMapping("/searchtask/{userid}")
    public User searchTaskById(@PathVariable String userid) throws TaskNotFoundException {
        return taskService.searchTaskById(userid);
    }

    @DeleteMapping("/deletetask/{userid}/{taskid}")
    public Boolean deleteTaskById(@PathVariable String userid, @PathVariable String taskid) throws TaskNotFoundException {
        if(taskService.deleteTask(userid,taskid)){
            return true;
        }
        else{
            throw new TaskNotFoundException();
        }
    }

    @PostMapping("/addtask/{userid}")
    public Boolean addTaskToList(@RequestBody Task task, @PathVariable String userid) throws UserNotFoundException {
        return taskService.addTask(task,userid);
    }

    @PutMapping("/updatetask/{userid}")
    public Boolean updateTask(@RequestBody Task task, @PathVariable String userid) throws TaskNotFoundException {
        return taskService.updateTask(task,userid);
    }
}
