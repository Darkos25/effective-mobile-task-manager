package com.example.EffectiveMobile.Config;

import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.OpenAPI;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Task Management API")
                        .version("1.0")
                        .description("API for managing tasks and comments")
                        .contact(new Contact()
                                .name("Alexandr Nazarov")
                                .email("pas58777@yandex.ru"))
                );
    }

    @Bean
    public GroupedOpenApi tasksApi() {
        return GroupedOpenApi.builder()
                .group("tasks")
                .pathsToMatch("/tasks/**")
                .build();
    }

    @Bean
    public GroupedOpenApi usersApi() {
        return GroupedOpenApi.builder()
                .group("users")
                .pathsToMatch("/users/**")
                .build();
    }
}
