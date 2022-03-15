package com.todo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.todo.exception.TaskNotFoundException;
import com.todo.exception.UserNotFoundException;
import com.todo.model.Task;
import com.todo.model.User;
import com.todo.repository.ArchiveRepository;
import com.todo.service.ArchiveServiceImpl;

import org.junit.jupiter.api.*;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@SpringBootTest
public class ServiceTest {

    private User user;
    private Task task;
    private List<Task> tasks = new ArrayList<Task>();

    @Mock
    private ArchiveRepository repository;

    @InjectMocks
    private ArchiveServiceImpl service = new ArchiveServiceImpl();

    @BeforeEach
    public void setup() throws Exception {
        task = new Task("TID001","Friday Marketing","apple,banana,orange",true,"grocery","27/11/1997");
        tasks.add(task);
        user = new User("UID001","Umesh","um123",tasks);
    }

    @AfterEach
    public void tearDown() {
        task = null;
        tasks.clear();
        user = null;
    }

    @Test
    public void addTaskTest() throws UserNotFoundException {
        when(repository.findById("UID001")).thenReturn(Optional.ofNullable(user));
        when(repository.save(user)).thenReturn(user);
        assertEquals(true,service.addTask(task,"UID001"));
    }

    @Test
    public void deleteTaskTest() throws TaskNotFoundException {
        when(repository.findById("UID001")).thenReturn(Optional.ofNullable(user));
        when(repository.save(user)).thenReturn(user);
        assertEquals(true,service.deleteTask("UID001","TID001"));
    }

    @Test
    public void updateTaskTest() throws TaskNotFoundException {
        when(repository.findById("UID001")).thenReturn(Optional.ofNullable(user));
        when(repository.save(user)).thenReturn(user);
        assertEquals(true,service.updateTask(task,"UID001"));
    }

    @Test
    public void getAllTasksTest() {
        when(repository.findById("UID001")).thenReturn(Optional.ofNullable(user));
        assertEquals(tasks,service.getAllTask("UID001"));
    }

}
