package com.example.todo.controller;

import com.example.todo.service.TodoH2Service;
import com.example.todo.model.Todo;

import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.*;

@RestController
public class TodoController {

    @Autowired 
    public TodoH2Service todoObj;

    @DeleteMapping("/todos/{id}") 
    public void deleteTodo(@PathVariable("id") int id) {
        todoObj.deleteTodo(id);
    }

    @PutMapping("/todos/{id}")
    public Todo updateTodo(@PathVariable("id") int id, @RequestBody Todo todo) {
        return todoObj.updateTodo(id, todo);
    }

    @GetMapping("/todos/{id}")
    public Todo getTodoById(@PathVariable("id") int id) {
        return todoObj.getTodoById(id);
    }

    @PostMapping("/todos")
    public Todo addTodo(@RequestBody Todo todo) {
        return todoObj.addTodo(todo);
    }

    @GetMapping("/todos") 
    public ArrayList<Todo> getTodos() {
        return todoObj.getTodos();
    }
}