package com.example.EffectiveMobile.Repository;

import com.example.EffectiveMobile.Model.Task;
import com.example.EffectiveMobile.Model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {

    Task findTaskByTaskName(String taskName);

    List<Task> findAllTasks();

    Page<Task> findAll(Specification<Task> spec, PageRequest pageRequest);

    Task findTaskById(Long id);

    Task deleteTaskById(Long id);

    Task findTaskByAuthor(User author);

    Task findTaskByAssignee(User assignee);
}
