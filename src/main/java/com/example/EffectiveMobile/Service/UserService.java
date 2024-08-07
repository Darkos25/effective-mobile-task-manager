package com.example.EffectiveMobile.Service;

import com.example.EffectiveMobile.Model.User;
import com.example.EffectiveMobile.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public List<User> findAll(){
        return userRepository.findAll();
    }

    public User findById(Long id) {
        return userRepository.findUserById(id);
    }

    public User save(User user) {
        return userRepository.save(user);
    }

    public User update(User user) {
        return userRepository.save(user);
    }

    public User delete(Long id) {
        return userRepository.deleteUserById(id);
    }
}
