package com.in28minutes.springboot.web;

import com.in28minutes.springboot.web.entity.User;
import com.in28minutes.springboot.web.model.Todo;
import com.in28minutes.springboot.web.repository.TodoRepository;
import com.in28minutes.springboot.web.repository.UserRepository;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class StartupCommandLineRunner implements CommandLineRunner {
    private static final Log LOGGER = LogFactory.getLog(StartupCommandLineRunner.class);
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final TodoRepository todoRepository;

    @Autowired
    public StartupCommandLineRunner(UserRepository userRepository,
                                    PasswordEncoder passwordEncoder,
                                    TodoRepository todoRepository) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.todoRepository = todoRepository;
    }


    @Override
    public void run(String... args) throws Exception {
        User yaser = new User("Yaser", passwordEncoder.encode("dummy1"), "ADMIN");
        User hadi = new User("Hadi", passwordEncoder.encode("dummy2"), "USER");
        userRepository.save(yaser);
        userRepository.save(hadi);

        todoRepository.save(new Todo("Yaser", "Learn Microservices", new Date(), false));
        todoRepository.save(new Todo("Yaser", "Learn Spring Cloud", new Date(), false));
        todoRepository.save(new Todo("Hadi", "Learn Big Data", new Date(), false));
        todoRepository.save(new Todo("Hadi", "Learn Stream programming", new Date(), false));

        LOGGER.info(String.format("Users and Todos for two user %s and %s has been saved in DB.",
                yaser.getUsername(), hadi.getUsername()));
    }
}