package com.example.EffectiveMobile.Service;

import com.example.EffectiveMobile.Model.Comment;
import com.example.EffectiveMobile.Model.Task;
import com.example.EffectiveMobile.Repository.CommentRepository;
import com.example.EffectiveMobile.Repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
public class CommentService {

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private TaskRepository taskRepository;

    public Page<Comment> getCommentsByTask(Long taskId, int page) {
        Task task = taskRepository.findById(taskId).orElse(null);
        if (task == null) {
            return Page.empty();
        }
        return commentRepository.findCommentByTaskId(taskId, PageRequest.of(page - 1, 10));
    }

    public Comment addCommentToTask(Long taskId, Comment comment) {
        Task task = taskRepository.findById(taskId).orElse(null);
        if (task != null) {
            comment.setTask(task);
            return commentRepository.save(comment);
        }
        return null;
    }

    public void deleteComment(Long taskId, Long commentId) {
        Comment comment = commentRepository.findById(commentId).orElse(null);
        if (comment != null && comment.getTask().getId().equals(taskId)) {
            commentRepository.delete(comment);
        }
    }


    public Comment updateComment(Long taskId, Long commentId, Comment updatedComment) {
        Comment existingComment = commentRepository.findById(commentId).orElse(null);
        if (existingComment != null && existingComment.getTask().getId().equals(taskId)) {
            existingComment.setComment(updatedComment.getComment());
            return commentRepository.save(existingComment);
        }
        return null;
    }
}
