package com.example.EffectiveMobile.Repository;

import com.example.EffectiveMobile.Model.Task;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;


@Repository
public interface TaskRepository extends JpaRepository<Task, Long>, JpaSpecificationExecutor<Task> {

    @EntityGraph(attributePaths = {"assignee", "author"})
    Page<Task> findAll(Specification<Task> spec, Pageable pageable);

    Task findTaskById(Long id);

    Task deleteTaskById(Long id);

}

