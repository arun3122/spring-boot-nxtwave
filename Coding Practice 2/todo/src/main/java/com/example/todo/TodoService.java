package com.example.todo;

import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;

import java.util.*;

// Do not modify the below code

public class TodoService implements TodoRepository {

    private static HashMap<Integer, Todo> todoList = new HashMap<>();

    public TodoService() {
        todoList.put(1, new Todo(1, "Watch Movie", "LOW", "TO DO"));
        todoList.put(2, new Todo(2, "Finish Project", "HIGH", "IN PROGRESS"));
        todoList.put(3, new Todo(3, "Buy Groceries", "MEDIUM", "TO DO"));
        todoList.put(4, new Todo(4, "Learning from NxtWave", "HIGH", "IN PROGRESS"));
        todoList.put(5, new Todo(5, "Go for a Run", "MEDIUM", "DONE"));

    }

    // Do not modify the above code
    int uniqueId = 6;

    @Override
    public void deleteTodo(int id) {
        Todo delTodo = todoList.get(id);
        if (delTodo == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        } else {
            todoList.remove(id);
            throw new ResponseStatusException(HttpStatus.NO_CONTENT);
        }
    }

    @Override
    public Todo updateTodo(int id, Todo todo) {
        Todo existingTodo = todoList.get(id);
        if (existingTodo == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        if (existingTodo != null) {
            existingTodo.setTodo(todo.getTodo());
        }

        if (existingTodo != null) {
            existingTodo.setPriority(todo.getPriority());
        }

        if (existingTodo != null) {
            existingTodo.setStatus(todo.getStatus());
        }

        return existingTodo;
    }

    @Override
    public Todo getTodoById(int id) {
        Todo uniqueTodo = todoList.get(id);
        if (uniqueTodo == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        return uniqueTodo;
    }

    @Override
    public Todo addTodo(Todo todo) {
        todo.setId(uniqueId);
        todoList.put(uniqueId, todo);
        uniqueId+=1;

        return todo;
    }

    @Override
    public ArrayList<Todo> getTodos() {
        Collection<Todo> allTodos = todoList.values();
        ArrayList<Todo> todos = new ArrayList<>(allTodos);
        return todos;
    }

}
