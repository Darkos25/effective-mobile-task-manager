package com.example.EffectiveMobile.Model;

import com.example.EffectiveMobile.DTO.CommentDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "comments")
public class Comment {
    @Id
    @Column(name = "id", nullable = false, unique = true)
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "comment", columnDefinition = "TEXT")
    private String comment;

    @ManyToOne(fetch = FetchType.LAZY)
    private Task task;
    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    public CommentDTO commentToCommentDto(Comment comment) {

        CommentDTO commentDTO = new CommentDTO();
        commentDTO.setComment(comment.getComment());
        commentDTO.setId(comment.getId());

        return commentDTO;
    }

}
