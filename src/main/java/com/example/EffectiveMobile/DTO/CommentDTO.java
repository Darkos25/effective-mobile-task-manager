package com.example.EffectiveMobile.DTO;

import com.example.EffectiveMobile.Model.Comment;
import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommentDTO {

    @SerializedName("comment_id")
    private Long id;
    @SerializedName("comment")
    private String comment;

    public Comment commentDTOToComment (CommentDTO commentDTO) {
        Comment comment = new Comment();
        comment.setId(commentDTO.getId());
        comment.setComment(commentDTO.getComment());

        return comment;
    }
}
