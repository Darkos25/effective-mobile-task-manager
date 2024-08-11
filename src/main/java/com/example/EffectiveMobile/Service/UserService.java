package com.example.EffectiveMobile.Service;

import com.example.EffectiveMobile.DTO.UserDTO;
import com.example.EffectiveMobile.Model.User;
import com.example.EffectiveMobile.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;


    public List<UserDTO> findAllUsers(){
        List<User> users = userRepository.findAll();
        List<UserDTO> userDTOs = new ArrayList<>();
        for (User user : users) {
            userDTOs.add(user.userToUserDto(user));
        }
        return userDTOs;
    }

    public User findById(Long id) {
        return userRepository.findUserById(id);
    }

    public User save(User user) {
        return userRepository.save(user);
    }

    public User delete(Long id) {
        return userRepository.deleteUserById(id);
    }

    public User findByEmail(String email) {
        return userRepository.findUserByEmail(email);
    }
}
