package com.example.EffectiveMobile.Service;

import com.example.EffectiveMobile.Model.Task;
import com.example.EffectiveMobile.Repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskService {
    @Autowired
    private TaskRepository taskRepository;

    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    public Task getTaskById(Long id) {
        return taskRepository.findTaskById(id);
    }

    public Task saveTask(Task task) {
        return taskRepository.save(task);
    }

    public Task deleteTask(Long id) {
        return taskRepository.deleteTaskById(id);
    }

}
