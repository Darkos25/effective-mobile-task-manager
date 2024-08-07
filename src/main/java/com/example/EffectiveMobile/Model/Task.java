package com.example.EffectiveMobile.Model;

import com.example.EffectiveMobile.DTO.TaskDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tasks")
public class Task {
    @Id
    @Column(name = "id", nullable = false, unique = true)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "task_name")
    private String taskName;
    @Column(name = "description", columnDefinition = "TEXT")
    private String description;
    @Column (name = "status")
    @Enumerated(EnumType.STRING)
    private Status status;
    @Column(name = "priority")
    @Enumerated(EnumType.STRING)
    private Priority priority;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Comment> comments;

    @ManyToOne
    @JoinColumn(name = "author_id", nullable = false)
    private User author;

    @ManyToOne
    @JoinColumn(name = "assignee_id")
    private User assignee;

    public TaskDTO TaskToDTO (Task task) {
        TaskDTO taskDTO = new TaskDTO();
        taskDTO.setTaskName(task.getTaskName());
        taskDTO.setDescription(task.getDescription());
        taskDTO.setPriority(task.getPriority());
        taskDTO.setStatus(task.getStatus());
        taskDTO.setAuthor(task.getAuthor().userToDto(task.getAuthor()));
        taskDTO.setAssignee(task.getAssignee().userToDto(task.getAssignee()));
        return taskDTO;
    }

}
