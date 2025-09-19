/*
 * You can use the following import statements
 * 
 * import org.springframework.web.bind.annotation.*;
 * import java.util.*;
 * 
 */


package com.example.employee;

import org.springframework.web.bind.annotation.*;
import java.util.*;

@RestController
class EmployeeController {
    EmployeeService employeeObj = new EmployeeService();

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
        employeeObj.addEmployee(employee);
        return employee;
    }

    @GetMapping("/employees")
    public ArrayList<Employee> getEmployees() {
        return employeeObj.getEmployees();
    }
}