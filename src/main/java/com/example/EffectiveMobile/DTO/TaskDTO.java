package com.example.EffectiveMobile.DTO;

import com.example.EffectiveMobile.Model.Priority;
import com.example.EffectiveMobile.Model.Status;
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
    @SerializedName("author")
    private String author;
    @SerializedName("assignee")
    private String assignee;
    @SerializedName("comments")
    private List<CommentDTO> comments;

}
