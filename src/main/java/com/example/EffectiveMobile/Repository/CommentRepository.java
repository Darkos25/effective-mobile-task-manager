package com.example.EffectiveMobile.Repository;

import com.example.EffectiveMobile.Model.Comment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {

    Comment findCommentById(Long id);

    Page<Comment> findCommentByTaskId(Long taskId, PageRequest pageRequest);
}
