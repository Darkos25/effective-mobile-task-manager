package com.example.EffectiveMobile.Controller;

import com.example.EffectiveMobile.DTO.CommentDTO;
import com.example.EffectiveMobile.DTO.TaskDTO;
import com.example.EffectiveMobile.Model.Comment;
import com.example.EffectiveMobile.Model.Task;
import com.example.EffectiveMobile.Service.CommentService;
import com.example.EffectiveMobile.Service.TaskService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/tasks")
@Tag(name = "Task Management", description = "APIs for managing tasks")
public class TaskController {

    @Autowired
    private TaskService taskService;

    @Autowired
    private CommentService commentService;

    private final int PAGE_SIZE = 10;

    @GetMapping
    @Operation(summary = "Get all tasks", description = "Returns all tasks by author/assignee")
    public ResponseEntity<Page<TaskDTO>> getAllTasks(
            @RequestParam(required = false) String author,
            @RequestParam(required = false) String assignee,
            @RequestParam(defaultValue = "false") boolean withComments,
            @RequestParam(defaultValue = "1") int page) {

        Pageable pageable = PageRequest.of(page - 1, PAGE_SIZE);

        Page<Task> tasks = taskService.getTasks(author, assignee, withComments, pageable);

        List<TaskDTO> taskDtos = tasks.getContent()
                .stream()
                .map(task -> task.taskToTaskDTO(task))
                .collect(Collectors.toList());

        Page<TaskDTO> taskDtoPage = new PageImpl<>(taskDtos, pageable, tasks.getTotalElements());

        return ResponseEntity.ok(taskDtoPage);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get Task by ID", description = "Returns a task by its ID")
    public ResponseEntity<TaskDTO> getTaskById(@PathVariable Long id) {
        Task task = taskService.getTaskById(id);
        if (task != null) {
            TaskDTO taskDto = task.taskToTaskDTO(task);
            return ResponseEntity.ok(taskDto);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    @Operation(summary = "Create a new Task", description = "Creates a new task")
    public ResponseEntity<TaskDTO> createTask(@RequestBody TaskDTO taskDTO) {
        Task task = taskDTO.taskDTOToTask(taskDTO);
        taskService.saveTask(task);
        TaskDTO createdTaskDto = task.taskToTaskDTO(task);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdTaskDto);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update task", description = "Update task by id")
    public ResponseEntity<TaskDTO> updateTask(@PathVariable Long id, @RequestBody TaskDTO taskDTO) {
        Task existingTask = taskService.getTaskById(id);
        if (existingTask != null) {
            Task updatedTask = taskDTO.taskDTOToTask(taskDTO);
            updatedTask.setId(id);
            updatedTask = taskService.saveTask(updatedTask);
            TaskDTO updatedTaskDto = updatedTask.taskToTaskDTO(updatedTask);
            return ResponseEntity.ok(updatedTaskDto);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete task", description = "Delete task by id")
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
    @Operation(summary = "Take all comments for task", description = "Take all comments for task")
    public ResponseEntity<Page<CommentDTO>> getCommentsByTask(
            @PathVariable Long taskId,
            @RequestParam(defaultValue = "1") int page) {

        Page<Comment> comments = commentService.getCommentsByTask(taskId, page);

        List<CommentDTO> commentDtos = comments.getContent()
                .stream()
                .map(comment -> comment.commentToCommentDto(comment))
                .collect(Collectors.toList());

        Page<CommentDTO> commentDtoPage = new PageImpl<>(commentDtos, comments.getPageable(), comments.getTotalElements());

        return ResponseEntity.ok(commentDtoPage);
    }

    @PostMapping("/{taskId}/comments")
    @Operation(summary = "Create a new comment for task", description = "Create a new comment for task by task id")
    public ResponseEntity<CommentDTO> addCommentToTask(
            @PathVariable Long taskId,
            @RequestBody CommentDTO commentDto) {

        Comment comment = commentDto.commentDTOToComment(commentDto);
        Comment createdComment = commentService.addCommentToTask(taskId, comment);
        if (createdComment != null) {
            CommentDTO createdCommentDto = comment.commentToCommentDto(comment);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdCommentDto);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{taskId}/comments/{commentId}")
    @Operation(summary = "Update comment for task", description = "Update comment for task by task id and comment id")
    public ResponseEntity<CommentDTO> updateComment(
            @PathVariable Long taskId,
            @PathVariable Long commentId,
            @RequestBody CommentDTO commentDto) {

        Comment comment = commentDto.commentDTOToComment(commentDto);
        Comment updatedComment = commentService.updateComment(taskId, commentId, comment);
        if (updatedComment != null) {
            CommentDTO updatedCommentDto = comment.commentToCommentDto(comment);
            return ResponseEntity.ok(updatedCommentDto);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{taskId}/comments/{commentId}")
    @Operation(summary = "Delete comment for task", description = "Delete comment for task by task id and comment id")
    public ResponseEntity<Void> deleteComment(
            @PathVariable Long taskId,
            @PathVariable Long commentId) {
        commentService.deleteComment(taskId, commentId);
        return ResponseEntity.noContent().build();
    }

}
