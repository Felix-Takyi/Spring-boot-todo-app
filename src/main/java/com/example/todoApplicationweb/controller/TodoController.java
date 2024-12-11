package com.example.todoApplicationweb.controller;

import com.example.todoApplicationweb.model.Todo;
import com.example.todoApplicationweb.service.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/todos")
public class TodoController {
    @Autowired
    private TodoService todoService;

        @GetMapping
        public String listTasks(Model model) {
            model.addAttribute("todos", todoService.getAllTodos());
            return "todos";
        }

        @GetMapping("/new")
        public String showCreateForm(Model model) {
            model.addAttribute("todo", new Todo());
            return "create-todo";
        }

        @PostMapping
        public String saveTask(@ModelAttribute("todo") Todo todo) {
            todoService.saveTodo(todo);
            return "redirect:/todos";
        }

        @GetMapping("/edit/{id}")
        public String showEditForm(@PathVariable Long id, Model model) {
            model.addAttribute("todo", todoService.findById(id));
            return "edit-todo";
        }

        @PostMapping("/{id}")
        public String updateTask(@PathVariable Long id, @ModelAttribute("todo") Todo todo) {
            Todo existingTodo = todoService.findById(id);
            existingTodo.setDescription(todo.getDescription());
            existingTodo.setCompleted(todo.isCompleted());
            todoService.saveTodo(existingTodo);
            return "redirect:/todos";
        }

        @GetMapping("/delete/{id}")
        public String deleteTask(@PathVariable Long id) {
            todoService.deleteById(id);
            return "redirect:/todos";
        }
    }

