package com.todo;

import com.todo.model.Task;
import com.todo.model.User;
import com.todo.repository.ArchiveRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(SpringExtension.class)
@DataMongoTest
public class RepositoryLayerTesting {

    @Autowired
    ArchiveRepository taskRepository;

    private User user;
    private Task task;
    private List<Task> tasks = new ArrayList<Task>();

    @BeforeEach
    void setUp() throws Exception {
        task = new Task("TID001","Friday Marketing","apple,banana,orange",true,"grocery","27/11/1997");
        tasks.add(task);
        user = new User("UID001","Umesh","um123",tasks);
    }

    @AfterEach
    void tearDown() throws Exception {
        taskRepository.deleteById("UID001");
    }

    @Test
    public void testInsertionOfTask() {
        taskRepository.insert(user);
        User user1 = taskRepository.findById(user.getUserId()).get();
        assertEquals(user1.getUserId(),user.getUserId());
    }

    @Test
    public void testSaveTask() {
        taskRepository.save(user);
        User user1 = taskRepository.findById(user.getUserId()).get();
        assertEquals(user1.getUserId(),user.getUserId());
    }

    @Test
    public void testDeleteProduct() {
        taskRepository.insert(user);
        User user1 = taskRepository.findById(user.getUserId()).get();
        taskRepository.deleteById(user1.getUserId());
        assertEquals(Optional.empty(), taskRepository.findById(user.getUserId()));
    }

    @Test
    public void testRetrieveData() {
      taskRepository.insert(user);
        assertEquals("Friday Marketing",task.getTaskTitle());
    }

    @Test
    public void testExistingData() {
        taskRepository.insert(user);
        assertTrue(taskRepository.existsById(user.getUserId()));
    }

}
