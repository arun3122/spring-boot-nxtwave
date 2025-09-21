package com.example.school.repository;

import com.example.school.model.Student;
import java.util.*;

public interface StudentRepository {
    ArrayList<Student> getStudents();
    Student addStudent(Student student);
    String addStudents(List<Student> students);
    Student getStudentById(int studentId);
    Student updateStudent(int studentId, Student student);
    void deleteStudent(int studentId);
}