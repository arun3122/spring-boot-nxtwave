package com.example.todo;

import org.springframework.web.bind.annotation.*;
import java.util.*;

@RestController
class TodoController {
    TodoService todoObj = new TodoService();

    @DeleteMapping("/todos/{id}")
    public void deleteTodo(@PathVariable int id) {
        todoObj.deleteTodo(id);
    }

    @PutMapping("/todos/{id}")
    public Todo updateTodo(@PathVariable int id, @RequestBody Todo todo) {
        return todoObj.updateTodo(id, todo);
    }

    @GetMapping("/todos/{id}")
    public Todo getTodoById(@PathVariable int id) {
        return todoObj.getTodoById(id);
    }

    @PostMapping("/todos") 
    public Todo addTodo(@RequestBody Todo todo) {
        todoObj.addTodo(todo);
        return todo;
    }

    @GetMapping("/todos")
    public ArrayList<Todo> getTodos() {
        return todoObj.getTodos();
    }
}