package com.in28minutes.springboot.web.controller;

import com.in28minutes.springboot.web.model.Todo;
import com.in28minutes.springboot.web.service.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
@SessionAttributes("name")
public class TodoController {
    private final TodoService todoService;

    @Autowired
    public TodoController(TodoService todoService) {
        this.todoService = todoService;
    }

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        binder.registerCustomEditor(Date.class, new CustomDateEditor(format, false));
    }

    @GetMapping("/list-todos")
    public String showTodosList(ModelMap model) {
        String name = (String) model.get("name");
        model.put("todos", todoService.retrieveTodos(name));
        return "list-todos";
    }

    @GetMapping("/add-todo")
    public String showAddTodoPage(ModelMap model) {
        //to be used as command name in todo.jsp
        model.addAttribute(
                "todo",
                new Todo(0,(String) model.get("name"), "Default Description", new Date(), false));
        return "todo";
    }

    @PostMapping("/add-todo")
    public String addTodo(ModelMap model, @Valid Todo todo, BindingResult result) {
        if(result.hasErrors()) {
            return "todo";
        }
        todoService.addTodo((String) model.get("name"), todo.getDesc(), todo.getTargetDate(), false);
        return "redirect:/list-todos";
    }

    @GetMapping("/delete-todo")
    public String deleteTodo(@RequestParam int id) {
        todoService.deleteTodo(id);
        return "redirect:/list-todos";
    }

    @GetMapping("/update-todo")
    public String showUpdateTodoPage(@RequestParam int id, ModelMap model) {
        Todo todo = todoService.retrieveTodo(id);
        model.addAttribute("todo", todo);
         return "todo";
    }

    @PostMapping("/update-todo")
    public String updateTodo(@Valid Todo todo, BindingResult result, ModelMap model) {
        if (result.hasErrors()) {
            return "todo";
        }

        todo.setUser((String) model.get("name"));
        todoService.updateTodo(todo);
        return "redirect:/list-todos";
    }


}
