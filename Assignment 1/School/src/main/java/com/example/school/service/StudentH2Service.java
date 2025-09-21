package com.example.school.service;

import com.example.school.model.Student;
import com.example.school.model.StudentRowMapper;
import com.example.school.repository.StudentRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import java.util.*;
import java.util.stream.Collectors;

@Service 
public class StudentH2Service implements StudentRepository {

    @Autowired
    private JdbcTemplate db;

    @Override
    public void deleteStudent(int studentId) {
        db.update("DELETE STUDENT WHERE studentId = ?", studentId);
    }

    @Override
    public Student updateStudent(int studentId, Student student) {
        if (student.getStudentName() != null) {
            db.update("UPDATE STUDENT SET studentName = ? WHERE studentId = ?", student.getStudentName(), studentId);
        }

        if (student.getGender() != null) {
            db.update("UPDATE STUDENT SET gender = ? WHERE studentId = ?", student.getGender(), studentId);
        }

        if (student.getStandard() > 0) {
            db.update("UPDATE STUDENT SET standard = ? WHERE studentId = ?", student.getStandard(), studentId);
        }

        return getStudentById(studentId);
    }

    @Override 
    public Student getStudentById(int studentId) {
        try {
            Student uniqueStudent = db.queryForObject("SELECT * FROM STUDENT WHERE studentId = ?", new StudentRowMapper(), studentId);
            return uniqueStudent;
        } catch(Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public String addStudents(List<Student> students) {
        students.stream()
                .forEach(student -> db.update("INSERT INTO STUDENT(studentName, gender, standard) VALUES(?, ?, ?)",
                                         student.getStudentName(), student.getGender(), student.getStandard()));
        return "Successfully added "+ students.size() + " students";
    }

    @Override
    public Student addStudent(Student student) {
        db.update("INSERT INTO STUDENT(studentName, gender, standard) VALUES(?, ?, ?)",
                                         student.getStudentName(), student.getGender(), student.getStandard());
        Student newStudent = db.queryForObject("SELECT * FROM STUDENT WHERE studentName = ? AND gender = ? AND standard = ?", new StudentRowMapper(), 
                                                student.getStudentName(), student.getGender(), student.getStandard());
        return newStudent;
    }

    @Override
    public ArrayList<Student> getStudents() {
        List<Student> studentsList = db.query("SELECT * FROM STUDENT", new StudentRowMapper());
        ArrayList<Student> students = new ArrayList<>(studentsList);
        return students;
    }
}