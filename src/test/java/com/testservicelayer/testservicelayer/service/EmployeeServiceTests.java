package com.testservicelayer.testservicelayer.service;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.testservicelayer.testservicelayer.entity.Employee;
import com.testservicelayer.testservicelayer.repository.EmployeeRepository;
import com.testservicelayer.testservicelayer.service.Impl.EmployeeServiceImpl;

@ExtendWith(MockitoExtension.class)
public class EmployeeServiceTests {
    
    @Mock
    private EmployeeRepository employeeRepository;

    @InjectMocks
    private EmployeeServiceImpl employeeService;

    @BeforeEach
    public void setUp()
    {
        // employeeRepository = Mockito.mock(EmployeeRepository.class);
        // employeeService    = new EmployeeServiceImpl(employeeRepository);
    }

    // JQunit test for save employee opertaions
    @Test
    @DisplayName("JQunit test for save employee opertaions")
    public void givenEmployeeObject_whenSaveEmployee_thenReturnEmployeeObject() {

        //   Given : Setup object or precondition
        Employee employee = Employee.builder()
                .id(1L)
                .firstName("MOHOSIN")
                .lastName("MIAH")
                .email("mohosinmiah1610@gmail.com")
                .departmentCode("CSE")
                .build();

        BDDMockito.given(employeeRepository.findByEmail(employee.getEmail())).willReturn(Optional.empty());

        BDDMockito.given(employeeRepository.save(employee)).willReturn(employee);

        // When: Action or behavior that we are going to test
        Employee saveEmployee = employeeService.saveEmployee(employee);

        // Then: Verify the output or expected result
        assertThat(saveEmployee).isNotNull();
    }



}
