package com.example.EffectiveMobile;

import com.example.EffectiveMobile.Model.Task;
import com.example.EffectiveMobile.Model.User;
import com.example.EffectiveMobile.Service.TaskService;
import com.example.EffectiveMobile.Service.UserService;
import jakarta.annotation.PostConstruct;
import jakarta.persistence.PostLoad;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class EffectiveMobileApplication {

	@Autowired
	private UserService userService;

	@Autowired
	private TaskService taskService;

	public static void main(String[] args) {
		SpringApplication.run(EffectiveMobileApplication.class, args);
	}

    @Bean
    public PasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }

	@PostConstruct
	public void postLoad() {
		User user = new User();
		user.setEmail("email@email.com");
		user.setPassword("$2a$10$Jovzfok/Ahc9ocxmE4AW/O7QXdwJX4oz4pp8eU8tgj/VKnKhCqNIC");
		user.setFullName("name");
		userService.save(user);

		Task task = new Task();
		task.setTaskName("task");
		task.setDescription("description");
		task.setAuthor(user);
		task.setAssignee(user);
		taskService.saveTask(task);

	}

}
