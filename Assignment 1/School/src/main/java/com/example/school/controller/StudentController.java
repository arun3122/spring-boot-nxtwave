package com.example.school.controller;

import com.example.school.service.StudentH2Service;
import com.example.school.model.Student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.*;


@RestController
public class StudentController {

    @Autowired
    public StudentH2Service studentObj;

    @DeleteMapping("/students/{studentId}")
    public void deleteStudent(@PathVariable("studentId") int studentId) {
        studentObj.deleteStudent(studentId);
    }

    @PutMapping("/students/{studentId}")
    public Student updateStudent(@PathVariable("studentId") int studentId, @RequestBody Student student) {
        return studentObj.updateStudent(studentId, student);
    }

    @GetMapping("/students/{studentId}")
    public Student getStudentById(@PathVariable("studentId") int studentId) {
        return studentObj.getStudentById(studentId);
    }

    @PostMapping("/students/bulk")
    public String addStudents(@RequestBody List<Student> students) {
        return studentObj.addStudents(students);
    }

    @PostMapping("/students")
    public Student addStudent(@RequestBody Student student) {
        return studentObj.addStudent(student);
    }

    @GetMapping("/students")
    public ArrayList<Student> getStudents() {
        return studentObj.getStudents();
    }
}