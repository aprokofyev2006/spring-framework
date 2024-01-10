package com.cydeo.service.impl;

import com.cydeo.dto.TaskDTO;
import com.cydeo.entity.Task;
import com.cydeo.mapper.TaskMapper;
import com.cydeo.repository.TaskRepository;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TaskServiceImplTest {

    @Mock
    TaskRepository taskRepository;

    @Mock
    TaskMapper taskMapper;

    @InjectMocks
    TaskServiceImpl taskService;

    @ParameterizedTest
    @ValueSource(longs = {1L, 2L, 3L, -5L})
    void findById_test(long id){

        //Given
        Task task = new Task();

        when(taskRepository.findById(id)).thenReturn(Optional.of(task));
        when(taskMapper.convertToDto(task)).thenReturn(new TaskDTO());

        //When
        taskService.findById(id);

        //Then
        verify(taskRepository).findById(id);
        verify(taskMapper).convertToDto(any(Task.class));
        verify(taskMapper).convertToDto(task);

        verify(taskRepository, never()).findById(-5L);

    }

    @ParameterizedTest
    @ValueSource(longs = {1L, 2L, 3L, -5L})
    void findById_bdd_test(long id){
        //Given
        Task task = new Task();
        given(taskRepository.findById(anyLong())).willReturn(Optional.of(task));
        given(taskMapper.convertToDto(task)).willReturn(new TaskDTO());

        //When
        taskService.findById(id);

        //Then (same as verify)
        then(taskRepository).should().findById(id);
        then(taskRepository).should(never()).findById(-5L);
    }

}