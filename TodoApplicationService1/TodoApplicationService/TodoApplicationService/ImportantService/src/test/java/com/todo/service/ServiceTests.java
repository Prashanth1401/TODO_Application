package com.todo.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import static org.mockito.Mockito.when;

import com.todo.exception.ImpNotFoundException;

import com.todo.model.Task;
import com.todo.model.User;
import com.todo.repository.ImpRepository;

import com.todo.service.ImpServiceImpl;

import org.junit.jupiter.api.*;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@SpringBootTest
public class ServiceTests {

    private User user;
    private Task task;
    private List<Task> tasks = new ArrayList<Task>();

    @Mock
    private ImpRepository impRepository;

    @InjectMocks
    private ImpServiceImpl service = new ImpServiceImpl();

    @BeforeEach
    public void setup() throws Exception {
        task = new Task("TID001","Friday Marketing","apple,banana,orange",true,"grocery","27/11/1997");
        tasks.add(task);
        user = new User("UID001","Souvik","sg123",tasks);
    }

    @AfterEach
    public void tearDown() {
        task = null;
        tasks.clear();
        user = null;
    }

    @Test
    public void addTaskTest() throws ImpNotFoundException {
        when(impRepository.findById("UID001")).thenReturn(Optional.ofNullable(user));
        when(impRepository.save(user)).thenReturn(user);
        assertEquals(true,service.addTask(task,"UID001"));
    }

    @Test
    public void deleteTaskTest() throws ImpNotFoundException {
        when(impRepository.findById("UID001")).thenReturn(Optional.ofNullable(user));
        when(impRepository.save(user)).thenReturn(user);
        assertEquals(true,service.deleteTask("UID001","TID001"));
    }

    @Test
    public void updateTaskTest() throws ImpNotFoundException {
        when(impRepository.findById("UID001")).thenReturn(Optional.ofNullable(user));
        when(impRepository.save(user)).thenReturn(user);
        assertEquals(true,service.updateTask(task,"UID001"));
    }

    @Test
    public void getAllTasksTest() {
        when(impRepository.findById("UID001")).thenReturn(Optional.ofNullable(user));
        assertEquals(tasks,service.getAllTask("UID001"));
    }

}
