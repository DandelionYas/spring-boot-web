package com.in28minutes.springboot.web.controller;

import com.in28minutes.springboot.web.model.Todo;
import com.in28minutes.springboot.web.service.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import java.util.Date;

@Controller
@SessionAttributes("name")
public class TodoController {
    private final TodoService todoService;

    @Autowired
    public TodoController(TodoService todoService) {
        this.todoService = todoService;
    }

    @GetMapping("/list-todos")
    public String showTodosList(ModelMap model) {
        String name = (String) model.get("name");
        model.put("todos", todoService.retrieveTodos(name));
        return "list-todos";
    }

    @GetMapping("/add-todo")
    public String showAddTodoPage(ModelMap model) {
        //to be used in
        model.addAttribute(
                "todo", new Todo(0,(String) model.get("name"), "", new Date(), false));
        return "todo";
    }

    @PostMapping("/add-todo")
    public String addTodo(ModelMap model, Todo todo) {
        todoService.addTodo((String) model.get("name"), todo.getDesc(), new Date(), false);
        return "redirect:/list-todos";
    }

    @GetMapping("/delete-todo")
    public String deleteTodo(@RequestParam int id) {
        todoService.deleteTodo(id);
        return "redirect:/list-todos";
    }
}
