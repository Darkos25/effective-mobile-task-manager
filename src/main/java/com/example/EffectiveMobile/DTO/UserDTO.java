package com.example.EffectiveMobile.DTO;

import com.example.EffectiveMobile.Model.User;
import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {

    @SerializedName("full_name")
    private String fullName;
    @SerializedName("email")
    private String email;
    @SerializedName("password")
    private String password;

    public User DtoToUser (UserDTO userDTO){
        User user = new User();
        user.setEmail(userDTO.getEmail());
        user.setFullName(userDTO.getFullName());
        user.setPassword(userDTO.getPassword());
        return user;
    }
}
