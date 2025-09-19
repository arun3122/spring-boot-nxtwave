package com.example.employee.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import java.util.*;

import com.example.employee.model.Employee;
import com.example.employee.repository.EmployeeRepository;
import com.example.employee.model.EmployeeRowMapper;


@Service 
public class EmployeeH2Service implements EmployeeRepository {

    @Autowired
    private JdbcTemplate db;

    public void deleteEmployee(int employeeId) {
        db.update("DELETE FROM employeelist WHERE employeeId = ?", employeeId);
    }

    public Employee updateEmployee(int employeeId, Employee employee) {
        if (employee.getEmployeeName() != null) {
            db.update("UPDATE employeelist SET employeeName = ? WHERE employeeId = ?", employee.getEmployeeName(), employeeId);
        }

        if (employee.getEmail() != null) {
            db.update("UPDATE employeelist SET email = ? WHERE employeeId = ?", employee.getEmail(), employeeId);
        }

        if (employee.getDepartment() != null) {
            db.update("UPDATE employeelist SET department = ? WHERE employeeId = ?", employee.getDepartment(), employeeId);
        }

        return getEmployeeById(employeeId);
    }

    public Employee getEmployeeById(int employeeId) {
        try {
            Employee emp = db.queryForObject("SELECT * FROM employeelist WHERE employeeId = ?", new EmployeeRowMapper(), employeeId);
            return emp;
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    public Employee addEmployee(Employee employee) {
        db.update("INSERT INTO employeelist(employeeName, email, department) VALUES(?, ?, ?)", employee.getEmployeeName(), employee.getEmail(), employee.getDepartment());
        Employee newEmp = db.queryForObject("SELECT * FROM employeelist WHERE employeeName = ? AND email = ? AND department = ?", new EmployeeRowMapper(), employee.getEmployeeName(), employee.getEmail(), employee.getDepartment());
        return newEmp;
    }

    public ArrayList<Employee> getEmployees() {
        List<Employee> employeeList = db.query("SELECT * FROM employeelist", new EmployeeRowMapper());
        ArrayList<Employee> employees = new ArrayList<>(employeeList);

        return employees;
    }
}