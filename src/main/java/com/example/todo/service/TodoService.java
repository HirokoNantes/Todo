package com.example.todo.service;

import com.example.todo.repository.TodoRepository;
import org.springframework.stereotype.Service;
import com.example.todo.entity.Todo;
import java.util.List;

@Service
public class TodoService {

    private final TodoRepository repository;

    public TodoService(TodoRepository repository) {
        this.repository = repository;
    }

    public List<Todo> findAll() {
        return repository.findAll();
    }

    public void save(Todo todo) {
        repository.save(todo);
    }

    public Todo findById(Long id) {
    return repository.findById(id).orElseThrow();
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }

}