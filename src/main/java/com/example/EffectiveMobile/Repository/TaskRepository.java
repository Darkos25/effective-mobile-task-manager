package com.example.EffectiveMobile.Repository;

import com.example.EffectiveMobile.Model.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {

    Task findTaskByTaskName(String taskName);

    Task findTaskById(Long id);

    Task deleteTaskById(Long id);
}
