package com.testservicelayer.testservicelayer.service;

import java.util.List;

import com.testservicelayer.testservicelayer.entity.Employee;

public interface EmployeeService {
    public Employee saveEmployee(Employee employee);

    public List<Employee> getEmployeeList();

    public Employee getEmployeeById(Long Id);
}
