package com.crud.tasks.service;

import com.crud.tasks.domain.Task;
import com.crud.tasks.repository.TaskRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DbSerivceTest {

    @Autowired
    private DbService dbService;

    @Autowired
    private TaskRepository repository;

    @Test
    public void testGetAllTasks(){
        //Given
        long countBefore = repository.count();
        Task task1 = new Task("test1", "text1");
        //when
        dbService.saveTask(task1);
        long countAfter = repository.count();
        //Then
        assertEquals(countBefore, countAfter-1);
        //Clean up
        dbService.deleteTask(task1.getId());
    }

    @Test
    public void testGetTaskById() {
        //Given
        Task task1 = new Task("test1", "text1");
        Task savedTask = dbService.saveTask(task1);
        //When
        Task retrievedTask = dbService.getTaskById(savedTask.getId());
        //Then
        assertEquals("test1", retrievedTask.getTitle());
        //Clean up
        dbService.deleteTask(task1.getId());
    }

    @Test
    public void testSaveTask() {
        //Given
        Task task1 = new Task("test2", "text2");
        Task savedTask = dbService.saveTask(task1);
        //When
        Task retrievedTask = dbService.getTaskById(savedTask.getId());
        //Then
        assertEquals("text2", retrievedTask.getContent());
        //Clean up
        dbService.deleteTask(task1.getId());
    }

    @Test
    public void testGetTask() {
        //Given
        Task task1 = new Task("test3", "text3");
        Task savedTask = dbService.saveTask(task1);
        //When
        Optional<Task> taskOptional = dbService.getTask(savedTask.getId());
        //Then
        assertEquals("test3", taskOptional.get().getTitle());
        //Clean up
        dbService.deleteTask(task1.getId());
    }

    @Test
    public void deleteTask() {
        //Given
        Task task1 = new Task("test4", "text4");
        long count = repository.count();
        dbService.saveTask(task1);
        List<Task> currentList = dbService.getAllTasks();
        //When
        dbService.deleteTask(task1.getId());
        //Then
        assertEquals(count, currentList.size()-1);

    }
}
