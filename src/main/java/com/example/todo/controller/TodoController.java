package com.example.todo.controller;

import com.example.todo.entity.Todo;
import com.example.todo.service.TodoService;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import jakarta.validation.Valid;

@Controller
@CrossOrigin(origins = "*")
public class TodoController {

    private final TodoService service;

    public TodoController(TodoService service) {
        this.service = service;
    }

    @GetMapping("/")
    public String list(Model model) {
        model.addAttribute("todos", service.findAllOrderByDueDate());
        model.addAttribute("todo", new Todo());
        return "todos";
    }

    @PostMapping("/add")
    public String add(@Valid @ModelAttribute Todo todo, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("todos", service.findAllOrderByDueDate());
            return "todos";}
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
    public String update(@Valid @ModelAttribute Todo todo, BindingResult result) {
        if (result.hasErrors()) {return "edit";}
        service.save(todo);
        return "redirect:/";
    }
}
   