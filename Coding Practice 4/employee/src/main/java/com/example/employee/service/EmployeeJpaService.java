package com.example.employee.service;

import com.example.employee.model.Employee;
import com.example.employee.repository.EmployeeJpaRepository;
import com.example.employee.repository.EmployeeRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import java.util.*;

@Service
public class EmployeeJpaService implements EmployeeRepository {

    @Autowired
    EmployeeJpaRepository employeeJpaRepository;

    @Override
    public void deleteEmployee(int employeeId) {
        try {
            employeeJpaRepository.deleteById(employeeId);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public Employee updateEmployee(int employeeId, Employee employee) {
        try {
            Employee existingEmployee = employeeJpaRepository.findById(employeeId).get();

            if (employee.getEmployeeName() != null) {
                existingEmployee.setEmployeeName(employee.getEmployeeName());
            }

            if (employee.getEmail() != null) {
                existingEmployee.setEmail(employee.getEmail());
            }

            if (employee.getDepartment() != null) {
                existingEmployee.setDepartment(employee.getDepartment());
            }

            employeeJpaRepository.save(existingEmployee);
            return existingEmployee;

        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public Employee getEmployeeById(int employeeId) {
        try {
            return employeeJpaRepository.findById(employeeId).get();
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        
    }

    @Override
    public Employee addEmployee(Employee employee) {
        employeeJpaRepository.save(employee);
        return employee;
    }

    @Override 
    public ArrayList<Employee> getEmployees() {
        List<Employee> employeeList = employeeJpaRepository.findAll();
        ArrayList<Employee> employees = new ArrayList<>(employeeList);
        return employees;
    }
}