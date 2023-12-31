package com.testservicelayer.testservicelayer.service.Impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.testservicelayer.testservicelayer.entity.Employee;
import com.testservicelayer.testservicelayer.exception.DataDuplicationException;
import com.testservicelayer.testservicelayer.repository.EmployeeRepository;
import com.testservicelayer.testservicelayer.service.EmployeeService;

@Service
public class EmployeeServiceImpl implements EmployeeService{

    private EmployeeRepository employeeRepository;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    // Save Employee
    @Override
    public Employee saveEmployee(Employee employee) {

        // Check email is alrady exist or not
        Optional<Employee> getEmployeeByEmail = employeeRepository.findByEmail(employee.getEmail());

        if(getEmployeeByEmail.isPresent())
        {
            throw new DataDuplicationException( "Employee email alrady exists: " + employee.getEmail() );
        }
        // Save employee 
        return employeeRepository.save(employee);
    }

    @Override
    public List<Employee> getEmployeeList() {
        // Get employee list
        return employeeRepository.findAll();
    }

    @Override
    public Employee getEmployeeById(Long Id) {
        // Get employee by Id
        return employeeRepository.findById(Id).get();
    }

   @Override
   public Employee updateEmployee(Long Id, Employee employee) {
        Employee getEmployee = employeeRepository.findById(Id).get();

        getEmployee.setFirstName("Test");
        getEmployee.setLastName("Last Name");
        getEmployee.setEmail("mohosin@gmail.com");
        getEmployee.setDepartmentCode("CSE");

        Employee updatedEmployee = employeeRepository.save( getEmployee );

        // Updated employee
        return updatedEmployee;
    }


    @Override
   public String deleteEmployeeById(Long Id) {
        Optional<Employee> getEmployee = employeeRepository.findById(Id);

        if( getEmployee.isPresent() )
        {
            employeeRepository.deleteById( Id );
        }
        else
        {
           throw new DataDuplicationException( "Employee email alrady exists: " + Id );
        }

        return "Delete Successfully";
    }
    
}
