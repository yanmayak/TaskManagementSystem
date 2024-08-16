package ru.yanmayak.taskmanagementsystem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories(entityManagerFactoryRef = "entityManagerF")
@SpringBootApplication
public class TaskManagementSystemApplication {

    public static void main(String[] args) {
        SpringApplication.run(TaskManagementSystemApplication.class, args);
    }

}
