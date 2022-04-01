package com.task.init.runners;

import com.task.init.models.database.Category;
import com.task.init.models.database.Role;
import com.task.init.models.database.User;
import com.task.init.repositories.CategoryRepository;
import com.task.init.repositories.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class Runner implements CommandLineRunner {

    private final UserRepository userRepository;
    private final CategoryRepository categoryRepository;

    public Runner(UserRepository userRepository, CategoryRepository categoryRepository) {
        this.userRepository = userRepository;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public void run(String... args) {
        if (!userRepository.existsById(1L)) {
            userRepository.save(new User("admin", new Role("ADMIN")));
            userRepository.save(new User("user", new Role("USER")));
            categoryRepository.save(new Category(1L, "Action"));
        }

    }
}
