package com.example.todo.service;

import com.example.todo.entity.Todo;
import com.example.todo.repository.TodoRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TodoServiceTest {

    @Mock
    private TodoRepository repository;

    @InjectMocks
    private TodoService service;

    @Test
    void findAll_全件取得できる() {
        Todo todo = new Todo();
        when(repository.findAll()).thenReturn(List.of(todo));

        List<Todo> result = service.findAll();

        assertThat(result).hasSize(1);
        verify(repository).findAll();
    }

    @Test
    void save_保存できる() {
        Todo todo = new Todo();

        service.save(todo);

        verify(repository).save(todo);
    }

    @Test
    void findById_存在する場合取得できる() {
        Todo todo = new Todo();
        when(repository.findById(1L)).thenReturn(Optional.of(todo));

        Todo result = service.findById(1L);

        assertThat(result).isEqualTo(todo);
    }

    @Test
    void findById_存在しない場合例外() {
        when(repository.findById(1L)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> service.findById(1L))
                .isInstanceOf(RuntimeException.class);
    }

    @Test
    void delete_削除できる() {
        service.delete(1L);

        verify(repository).deleteById(1L);
    }
}