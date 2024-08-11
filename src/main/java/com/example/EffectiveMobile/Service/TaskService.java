package com.example.EffectiveMobile.Service;

import com.example.EffectiveMobile.Model.Task;
import com.example.EffectiveMobile.Repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskService {

    @Autowired
    private TaskRepository taskRepository;

    public Task getTaskById(Long id) {
        return taskRepository.findTaskById(id);
    }

    public Task saveTask(Task task) {
        return taskRepository.save(task);
    }

    public Task deleteTask(Long id) {
        return taskRepository.deleteTaskById(id);
    }

    public Page<Task> getTasks(String author, String asigner, boolean withComments, Pageable pageable) {
        Specification<Task> spec = Specification.where(null);

        if (author != null && !author.isEmpty()) {
            spec = spec.and((root, query, cb) -> cb.equal(root.get("author"), author));
        }

        if (asigner != null && !asigner.isEmpty()) {
            spec = spec.and((root, query, cb) -> cb.equal(root.get("asigner"), asigner));
        }

        if (!withComments) {
            spec = spec.and((root, query, cb) -> cb.isEmpty(root.get("comments")));
        }

        return taskRepository.findAll(spec, pageable);
    }
}
