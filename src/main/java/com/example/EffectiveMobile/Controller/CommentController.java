package com.example.EffectiveMobile.Controller;

import com.example.EffectiveMobile.Model.User;
import com.example.EffectiveMobile.Service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CommentController {
    @Autowired
    private CommentService commentService;

    @GetMapping
    public List<User> getAllUsers() {
        return null;
    }
}
