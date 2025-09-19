/*
 * You can use the following import statements
 * 
 * import org.springframework.web.server.ResponseStatusException;
 * import org.springframework.http.HttpStatus;
 * 
 */


package com.example.employee;

import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;

import java.util.*;

// Do not modify the below code

public class EmployeeService implements EmployeeRepository {

    private static HashMap<Integer, Employee> employeeList = new HashMap<>();

    public EmployeeService() {
        employeeList.put(1, new Employee(1, "John Doe", "johndoe@example.com", "Marketing"));
        employeeList.put(2, new Employee(2, "Jane Smith", "janesmith@example.com", "Human Resources"));
        employeeList.put(3, new Employee(3, "Bob Johnson", "bjohnson@example.com", "Sales"));
        employeeList.put(4, new Employee(4, "Alice Lee", "alee@example.com", "IT"));
        employeeList.put(5, new Employee(5, "Mike Brown", "mbrown@example.com", "Finance"));
        employeeList.put(6, new Employee(6, "Sara Lee", "slee@example.com", "Operations"));

    }

    // Do not modify the above code

    int uniqueId = 7;

    @Override 
    public void deleteEmployee(int employeeId) {
        Employee emp = employeeList.get(employeeId);
        if (emp == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        } else {
            employeeList.remove(employeeId);
            throw new ResponseStatusException(HttpStatus.NO_CONTENT);
        }
    }

    @Override
    public Employee updateEmployee(int employeeId, Employee employee) {
        Employee existingEmployee = employeeList.get(employeeId);
        if (existingEmployee == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        if (existingEmployee != null) {
            existingEmployee.setEmployeeName(employee.getEmployeeName());
        }

        if (existingEmployee != null) {
            existingEmployee.setEmail(employee.getEmail());
        }

        if (existingEmployee != null) {
            existingEmployee.setDepartment(employee.getDepartment());
        }

        return existingEmployee;
    }

    @Override 
    public Employee getEmployeeById(int employeeId){
        Employee uniqueEmp = employeeList.get(employeeId);
        if (uniqueEmp == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        return uniqueEmp;
    }

    @Override 
    public Employee addEmployee(Employee employee) {
        employee.setEmployeeId(uniqueId);
        employeeList.put(uniqueId, employee);
        uniqueId+=1;
        
        return employee;
    }

    @Override
    public ArrayList<Employee> getEmployees() {
        Collection<Employee> employees = employeeList.values();
        ArrayList<Employee> employeesList = new ArrayList<>(employees);
        return employeesList;
    }

}
