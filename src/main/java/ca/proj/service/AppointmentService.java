package ca.proj.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ca.proj.dtos.AppointmentDTO;
import ca.proj.entity.AppointmentEntity;
import ca.proj.mapper.AppointmentMapper;
import ca.proj.repository.IAppointmentRepository;
import ca.proj.repository.IEmployeeRepository;
import ca.proj.repository.IPatientRepository;

@Service
public class AppointmentService {
  @Autowired IAppointmentRepository appointmentRepository;
  @Autowired IEmployeeRepository employeeRepository;
  @Autowired IPatientRepository patientRepository;

  public AppointmentDTO createAppointment(AppointmentDTO appointment) {
    AppointmentEntity newAppointment = AppointmentMapper.INSTANCE.toEntity(appointment);
    newAppointment.setEmployee(employeeRepository.findById(appointment.getEmployeeUsername()).orElseThrow(() -> new RuntimeException("Employee not found")));
    newAppointment.setPatient(patientRepository.findById(appointment.getPatientUsername()).orElseThrow(() -> new RuntimeException("Patient not found")));
    appointmentRepository.save(newAppointment);
    return appointment;
  }
  
}
