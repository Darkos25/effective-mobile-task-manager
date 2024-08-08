package com.example.EffectiveMobile.Controller;

import com.example.EffectiveMobile.DTO.TaskDTO;
import com.example.EffectiveMobile.Model.Comment;
import com.example.EffectiveMobile.Model.Task;
import com.example.EffectiveMobile.Service.CommentService;
import com.example.EffectiveMobile.Service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tasks")
public class TaskController {

    @Autowired
    private TaskService taskService;

    @Autowired
    private CommentService commentService;

    @GetMapping
    public ResponseEntity<Page<Task>> getAllTasks(
            @RequestParam(required = false) String author,
            @RequestParam(required = false) String assignee,
            @RequestParam(defaultValue = "false") boolean comments,
            @RequestParam(defaultValue = "1") int page) {

        PageRequest pageRequest = PageRequest.of(page - 1, 15);

        Page<Task> tasks = taskService.getTasks(author, assignee, comments, pageRequest);

        return ResponseEntity.ok(tasks);
    }

    @GetMapping("/{id}")
    public Task getTaskById(@PathVariable Long id) {
        return taskService.getTaskById(id);
    }

    @PostMapping
    public ResponseEntity<Task> createTask(@RequestBody Task task) {
        Task createdTask = taskService.saveTask(task);
        return ResponseEntity.ok(createdTask);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Task> updateTask(@PathVariable Long id, @RequestBody Task task) {
        Task existingTask = taskService.getTaskById(id);
        if (existingTask != null) {
            task.setId(id);
            Task updatedTask = taskService.saveTask(task);
            return ResponseEntity.ok(updatedTask);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTask(@PathVariable Long id) {
        Task existingTask = taskService.getTaskById(id);
        if (existingTask != null) {
            taskService.deleteTask(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/{taskId}/comments")
    public ResponseEntity<Page<Comment>> getCommentsByTask(
            @PathVariable Long taskId,
            @RequestParam(defaultValue = "1") int page) {

        Page<Comment> comments = commentService.getCommentsByTask(taskId, page);
        return ResponseEntity.ok(comments);
    }

    @PostMapping("/{taskId}/comments/{commentId}")
    public ResponseEntity<Comment> addCommentToTask(
            @PathVariable Long taskId,
            @RequestBody Comment comment) {

        Comment createdComment = commentService.addCommentToTask(taskId, comment);
        if (createdComment != null) {
            return ResponseEntity.ok(createdComment);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{taskId}/comments/{commentId}")
    public ResponseEntity<Comment> updateComment(
            @PathVariable Long taskId,
            @PathVariable Long commentId,
            @RequestBody Comment comment) {

        Comment updatedComment = commentService.updateComment(taskId, commentId, comment);
        if (updatedComment != null) {
            return ResponseEntity.ok(updatedComment);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{taskId}/comments/{commentId}")
    public ResponseEntity<Void> deleteComment(
            @PathVariable Long taskId,
            @PathVariable Long commentId) {

        commentService.deleteComment(taskId, commentId);
        return ResponseEntity.noContent().build();
    }

    public TaskDTO TaskToDTO (Task task) {

        TaskDTO taskDTO = new TaskDTO();
        taskDTO.setTaskName(task.getTaskName());
        taskDTO.setDescription(task.getDescription());
        taskDTO.setPriority(task.getPriority());
        taskDTO.setStatus(task.getStatus());
        // Как лучше сделать ДТОху? Сделать юзер или по какому то параметру вызывать юзера?
//        taskDTO.setAuthor(task.getAuthor().getFullName());
//        taskDTO.setAssignee(task.getAssignee().getFullName());
        return taskDTO;
    }


}
