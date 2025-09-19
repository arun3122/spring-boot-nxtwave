package com.example.employee.controller;

import com.example.employee.model.Employee;
import com.example.employee.service.EmployeeH2Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.*;

@RestController
public class EmployeeController {

    @Autowired
    public EmployeeH2Service employeeObj;

    @DeleteMapping("/employees/{employeeId}") 
    public void deleteEmployee(@PathVariable("employeeId") int employeeId) {
        employeeObj.deleteEmployee(employeeId);
    }

    @PutMapping("/employees/{employeeId}") 
    public Employee updateEmployee(@PathVariable("employeeId") int employeeId, @RequestBody Employee employee) {
        return employeeObj.updateEmployee(employeeId, employee);
    }

    @GetMapping("/employees/{employeeId}")
    public Employee getEmployeeById(@PathVariable("employeeId") int employeeId) {
        return employeeObj.getEmployeeById(employeeId);
    }

    @PostMapping("/employees")
    public Employee addEmployee(@RequestBody Employee employee) {
        return employeeObj.addEmployee(employee);
    }

    @GetMapping("/employees")
    public ArrayList<Employee> getEmployees() {
        return employeeObj.getEmployees();
    }

}