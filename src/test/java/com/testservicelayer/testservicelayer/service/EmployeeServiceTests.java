package com.testservicelayer.testservicelayer.service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.testservicelayer.testservicelayer.entity.Employee;
import com.testservicelayer.testservicelayer.exception.DataDuplicationException;
import com.testservicelayer.testservicelayer.repository.EmployeeRepository;
import com.testservicelayer.testservicelayer.service.Impl.EmployeeServiceImpl;

@ExtendWith(MockitoExtension.class)
public class EmployeeServiceTests {

    @Mock
    private EmployeeRepository employeeRepository;

    @InjectMocks
    private EmployeeServiceImpl employeeService;

    private Employee employee;

    @BeforeEach
    public void setUp()
    {
        // employeeRepository = Mockito.mock(EmployeeRepository.class);
        // employeeService    = new EmployeeServiceImpl(employeeRepository);
        employee =  Employee.builder()
                .id(1L)
                .firstName("MOHOSIN")
                .lastName("MIAH")
                .email("mohosinmiah1610@gmail.com")
                .departmentCode("CSE")
                .build();
    }

    // JQunit test for save employee opertaions
    @Test
    @DisplayName("JQunit test for save employee opertaions")
    public void givenEmployeeObject_whenSaveEmployee_thenReturnEmployeeObject() {

        //   Given : Setup object or precondition

        BDDMockito.given(employeeRepository.findByEmail(employee.getEmail())).willReturn(Optional.empty());

        BDDMockito.given(employeeRepository.save(employee)).willReturn(employee);

        // When: Action or behavior that we are going to test
        Employee saveEmployee = employeeService.saveEmployee(employee);

        // Then: Verify the output or expected result
        assertThat(saveEmployee).isNotNull();
    }

    // JQunit test for save employee method which throws exception
    @Test
    @DisplayName("JQunit test for save employee method which throws exception")
    public void givenExistingEmail_whenSaveEmployee_thenThrowsException() {

        //   Given : Setup object or precondition

        BDDMockito.given(employeeRepository.findByEmail(employee.getEmail())).willReturn(Optional.of(employee));

        // BDDMockito.given(employeeRepository.save(employee)).willReturn(employee); As I will get error so there is no logical meaning to save

        // When: Action or behavior that we are going to test
        Assertions.assertThrows(DataDuplicationException.class, () -> {
            employeeService.saveEmployee(employee);
        });
        
    }

    // JQunit test for get all employee operations
    @Test
    @DisplayName("JQunit test for getEmployeeList operations")
    public void givenEmployeeObject_whenGetAllEmployee_thenReturnAllEmployee() {

       // Given: Setup objects or preconditions
        Employee employeeOne = Employee.builder()
            .id(1L)
            .firstName("MOHOSIN")
            .lastName("MIAH")
            .email("mohosinmiah1610@gmail.com")
            .departmentCode("CSE")
            .build();

        Employee employeeTwo = Employee.builder()
            .id(2L)  // Unique ID for the second employee
            .firstName("JOHN")
            .lastName("DOE")
            .email("johndoe@gmail.com")
            .departmentCode("IT")
            .build();

            // Mock the behavior of the employeeRepository
            List<Employee> mockedEmployees = Arrays.asList(employeeOne, employeeTwo);
            Mockito.when(employeeRepository.findAll()).thenReturn(mockedEmployees);

            // When: Perform the action or behavior being tested
            List<Employee> employees = employeeService.getEmployeeList();

            // Then: Verify the output or expected result
            assertThat(employees).isNotNull();
            assertThat(employees).hasSize(2);
            assertThat(employees).containsExactly(employeeOne, employeeTwo);
    }



}
