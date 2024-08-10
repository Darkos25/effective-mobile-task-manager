package com.example.EffectiveMobile.DTO;

import com.example.EffectiveMobile.Model.Priority;
import com.example.EffectiveMobile.Model.Status;
import com.example.EffectiveMobile.Model.Task;
import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TaskDTO {

    @SerializedName("task_name")
    private String taskName;
    @SerializedName("description")
    private String description;
    @SerializedName("status")
    private Status status;
    @SerializedName("priority")
    private Priority priority;
    @SerializedName("author_id")
    private Long authorId;
    @SerializedName("assignee_id")
    private Long assigneeId;
    @SerializedName("comments")
    private List<CommentDTO> comments;


//    autor : { id: 1, email: some@ui.com}

}
