package ca.proj.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ca.proj.dtos.EmployeeDTO;
import ca.proj.entity.EmployeeEntity;
import ca.proj.mapper.EmployeeMapper;
import ca.proj.repository.IEmployeeRepository;
import ca.proj.repository.IUserRepository;

@Service
public class EmployeeService {
  @Autowired IEmployeeRepository employeeRepository;
  @Autowired IUserRepository userRepository;

  public EmployeeDTO createEmployee(EmployeeDTO employee) {
    EmployeeEntity newEmployee = EmployeeMapper.INSTANCE.toEntity(employee);
    newEmployee.setUser(userRepository.findById(employee.getUsername()).orElseThrow(() -> new RuntimeException("User not found"))
    );
    employeeRepository.save(newEmployee);
    return employee;
  }

  public List<String> getAllDoctorsUsernames() {
    return employeeRepository.findAllDoctorsUsernames();
  }

  
}
