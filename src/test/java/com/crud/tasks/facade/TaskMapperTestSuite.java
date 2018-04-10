package com.crud.tasks.facade;

import com.crud.tasks.domain.Task;
import com.crud.tasks.domain.TaskDto;
import com.crud.tasks.mapper.TaskMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class TaskMapperTestSuite {
    @InjectMocks
    private TaskMapper taskMapper;

    @Test
    public void testMapToTask() {
        //Given
        TaskDto taskDto = new TaskDto(215L, "test_title", "test_content");
        //When
        Task taskToReturn = taskMapper.mapToTask(taskDto);
        //Then
        assertEquals(new Long(215), taskToReturn.getId());
        assertEquals("test_title", taskToReturn.getTitle());
        assertEquals("test_content", taskToReturn.getContent());
        assertNotNull(taskToReturn.getId());
    }

    @Test
    public void testMapToTaskDto() {
        //Given
        Task task = new Task(216L,"test_title", "test_content");
        //When
        TaskDto taskDtoToReturn = taskMapper.mapToTaskDto(task);
        //Then
        assertEquals(new Long(216L), taskDtoToReturn.getId());
        assertEquals("test_title", taskDtoToReturn.getTitle());
        assertEquals("test_content", taskDtoToReturn.getContent());
        assertNotNull(taskDtoToReturn.getId());
    }

    @Test
    public void testMapToTaskDtoList() {
        //Given
        List<Task> taskList = new ArrayList<>();
        taskList.add(new Task(111L, "title1", "content1"));
        taskList.add(new Task(222L, "title2", "content2"));
        //When
        List<TaskDto> taskDtoList = taskMapper.mapToTaskDtoList(taskList);
        //Then
        assertEquals(new Long(111), taskDtoList.get(0).getId());
        assertEquals("title2", taskDtoList.get(1).getTitle());
        assertEquals(2, taskDtoList.size());
    }
}
