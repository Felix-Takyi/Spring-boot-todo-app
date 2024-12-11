package com.example.todoApplicationweb.service;

import com.example.todoApplicationweb.model.Todo;
import com.example.todoApplicationweb.repository.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TodoService {
    @Autowired
    private TodoRepository todoRepository;

    public List<Todo> getAllTodos (){
        return todoRepository.findAll();
    }

    public Todo findById(Long id){
        return todoRepository.findById(id).orElse(new Todo());
    }

    public Todo saveTodo(Todo todo){
        return todoRepository.save(todo);
    }

    public void deleteById(Long id){
        todoRepository.deleteById(id);
    }

}
