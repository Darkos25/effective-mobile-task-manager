package com.example.EffectiveMobile;

import com.example.EffectiveMobile.Model.User;
import com.example.EffectiveMobile.Service.UserService;
import jakarta.annotation.PostConstruct;
import jakarta.persistence.PostLoad;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class EffectiveMobileApplication {

	@Autowired
	private UserService userService;

	public static void main(String[] args) {
		SpringApplication.run(EffectiveMobileApplication.class, args);
	}

	@PostConstruct
	public void postLoad() {
		User user = new User();
		user.setEmail("email@email.com");
		user.setPassword("password");
		user.setFullName("name");
		userService.save(user);

	}

}
