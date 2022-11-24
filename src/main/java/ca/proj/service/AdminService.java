package ca.proj.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ca.proj.dtos.AppointmentDTO;
import ca.proj.dtos.EmployeeDTO;
import ca.proj.dtos.PatientDTO;
import ca.proj.mapper.AppointmentMapper;
import ca.proj.mapper.EmployeeMapper;
import ca.proj.mapper.PatientMapper;
import ca.proj.repository.IAppointmentRepository;
import ca.proj.repository.IEmployeeRepository;
import ca.proj.repository.IPatientRepository;

@Transactional
@Service
public class AdminService {
  @Autowired IEmployeeRepository employeeRepository;
  @Autowired IPatientRepository patientRepository;
  @Autowired IAppointmentRepository appointmentRepository;

  public List<EmployeeDTO> getEmployees() {
    return EmployeeMapper.INSTANCE.toDto(employeeRepository.findAll());
  }

  public List<PatientDTO> getPatients() {
    return PatientMapper.INSTANCE.toDto(patientRepository.findAll());
  }

  public List<AppointmentDTO> getAppointments() {
    return AppointmentMapper.INSTANCE.toDto(appointmentRepository.findAll());
  }

  
}
