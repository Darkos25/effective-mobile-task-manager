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
    @SerializedName("author")
    private UserDTO author;
    @SerializedName("assignee")
    private UserDTO assignee;
    @SerializedName("comments")
    private List<CommentDTO> comments;

    public Task DTOToTask (TaskDTO taskDTO) {
        Task task = new Task();
        task.setTaskName(taskDTO.getTaskName());
        task.setDescription(taskDTO.getDescription());
        task.setPriority(taskDTO.getPriority());
        task.setStatus(taskDTO.getStatus());
        task.setAuthor(taskDTO.getAuthor().DtoToUser(taskDTO.getAuthor()));
        task.setAssignee(taskDTO.getAssignee().DtoToUser(taskDTO.getAssignee()));
        return task;
    }
}
