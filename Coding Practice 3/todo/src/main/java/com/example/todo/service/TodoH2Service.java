package com.example.todo.service;

import com.example.todo.model.Todo;
import com.example.todo.repository.TodoRepository;
import com.example.todo.model.TodoRowMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import java.util.*;

@Service
public class TodoH2Service implements TodoRepository {

    @Autowired
    private JdbcTemplate db;

    @Override
    public void deleteTodo(int id) {
        db.update("DELETE FROM todolist WHERE id = ?", id);
    }

    @Override
    public Todo updateTodo(int id, Todo todo) {
        if (todo.getTodo() != null) {
            db.update("UPDATE todolist SET todo = ? WHERE id = ?", todo.getTodo(), id);
        }

        if (todo.getStatus() != null) {
            db.update("UPDATE todolist SET status = ? WHERE id = ?", todo.getStatus(), id);
        }

        if (todo.getPriority() != null) {
            db.update("UPDATE todolist SET priority = ? WHERE id = ?", todo.getPriority(), id);
        }

        return getTodoById(id);
    }

    @Override
    public Todo getTodoById(int id) {
        try {
            Todo uniqueTodo = db.queryForObject("SELECT * FROM todolist WHERE id = ?", new TodoRowMapper(), id);
            return uniqueTodo;
        } catch(Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public Todo addTodo(Todo todo) {
        db.update("INSERT INTO todolist(todo, status, priority) VALUES(?, ?, ?)", todo.getTodo(), todo.getStatus(), todo.getPriority());
        Todo newTodo = db.queryForObject("SELECT * FROM todolist WHERE todo = ? AND status = ? AND priority = ?", new TodoRowMapper(), todo.getTodo(), todo.getStatus(), todo.getPriority());
        return newTodo;
    }

    @Override
    public ArrayList<Todo> getTodos() {
        List<Todo> todoLists = db.query("SELECT * FROM todolist" , new TodoRowMapper());
        ArrayList<Todo> todos = new ArrayList<>(todoLists);
        return todos;
    }
}