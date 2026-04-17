package com.example.todo.controller;

import com.example.todo.entity.Todo;
import com.example.todo.service.TodoService;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

@Controller
@CrossOrigin(origins = "*")
public class TodoController {

    private final TodoService service;

    public TodoController(TodoService service) {
        this.service = service;
    }

    @GetMapping("/")
    public String list(Model model) {
        model.addAttribute("todos", service.findAll());
        model.addAttribute("todo", new Todo());
        return "todos";
    }

    @PostMapping("/add")
    public String add(Todo todo) {
        service.save(todo);
        return "redirect:/";
    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        service.delete(id);
        return "redirect:/";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable Long id, Model model) {
        Todo todo = service.findById(id);
        model.addAttribute("todo", todo);
        return "edit";
    }

    @PostMapping("/update")
    public String update(Todo todo) {
        service.save(todo);
        return "redirect:/";
    }
}
   