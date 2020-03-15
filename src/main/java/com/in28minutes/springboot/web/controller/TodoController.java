package com.in28minutes.springboot.web.controller;

import com.in28minutes.springboot.web.exception.BadDescriptionFieldForTodoException;
import com.in28minutes.springboot.web.model.Todo;
import com.in28minutes.springboot.web.repository.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;

@Controller
@SessionAttributes("name")
public class TodoController {
    private final TodoRepository repository;

    @Autowired
    public TodoController(TodoRepository repository) {
        this.repository = repository;
    }

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        binder.registerCustomEditor(Date.class, new CustomDateEditor(format, false));
    }

    @GetMapping("/list-todos")
    public String showTodosList(ModelMap model) {
        String name = getLoggedInUsername(model);
        model.put("todos", repository.findAllByUser(name));
        return "list-todos";
    }

    @GetMapping("/add-todo")
    public String showAddTodoPage(ModelMap model) {
        //to be used as command name in todo.jsp
        model.addAttribute(
                "todo",
                new Todo(getLoggedInUsername(model), "Default Description", new Date(), false));
        return "todo";
    }

    @PostMapping("/add-todo")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String addTodo(ModelMap model, @Valid Todo todo, BindingResult result) {
        if (result.hasErrors()) {
            return "todo";
        }

        todo.setUser(getLoggedInUsername(model));
        repository.save(todo);
        return "redirect:/list-todos";
    }

    @GetMapping("/delete-todo")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String deleteTodo(@RequestParam int id) {
        repository.deleteById(id);
        return "redirect:/list-todos";
    }

    @GetMapping("/update-todo")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String showUpdateTodoPage(@RequestParam int id, ModelMap model) {
        Optional<Todo> todo = repository.findById(id);
        todo.ifPresent(a -> model.addAttribute("todo", todo.get()));
        return "todo";
    }

    @PostMapping("/update-todo")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String updateTodo(@Valid Todo todo, BindingResult result, ModelMap model) {
        if (result.hasErrors()) {
            /*
            * We only have bean validation on description field
            * This exception is just thrown for seeing the impact of TodoErrorHandler
            */
            throw new BadDescriptionFieldForTodoException();
        }

        todo.setUser(getLoggedInUsername(model));
        repository.save(todo);
        return "redirect:/list-todos";
    }

    private String getLoggedInUsername(ModelMap model) {
        return (String) model.get("name");
    }
}
