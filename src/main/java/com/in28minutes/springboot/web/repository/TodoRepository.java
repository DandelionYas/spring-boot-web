package com.in28minutes.springboot.web.repository;

import com.in28minutes.springboot.web.model.Todo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TodoRepository extends JpaRepository<Todo, Integer> {
    List<Todo> findAllByUser(String user);
}
