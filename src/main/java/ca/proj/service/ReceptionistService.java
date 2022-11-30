package ca.proj.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ca.proj.dtos.EmployeeDTO;
import ca.proj.mapper.EmployeeMapper;
import ca.proj.repository.IEmployeeRepository;

@Transactional
@Service
public class ReceptionistService {
  @Autowired IEmployeeRepository employeeRepository;

  public List<EmployeeDTO> getEmployeesNoAdmin() {
    return EmployeeMapper.INSTANCE.toDto(employeeRepository.findAllNoAdmin());
  }
  
}
