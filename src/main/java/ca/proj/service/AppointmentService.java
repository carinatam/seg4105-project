package ca.proj.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ca.proj.dtos.AppointmentDTO;
import ca.proj.dtos.PaymentDTO;
import ca.proj.dtos.PrescriptionDTO;
import ca.proj.entity.AppointmentEntity;
import ca.proj.mapper.AppointmentMapper;
import ca.proj.mapper.PaymentMapper;
import ca.proj.mapper.PrescriptionMapper;
import ca.proj.repository.IAppointmentRepository;
import ca.proj.repository.IEmployeeRepository;
import ca.proj.repository.IPatientRepository;
import ca.proj.repository.IPaymentRepository;
import ca.proj.repository.IPrescriptionRepository;

@Service
public class AppointmentService {
  @Autowired IAppointmentRepository appointmentRepository;
  @Autowired IEmployeeRepository employeeRepository;
  @Autowired IPatientRepository patientRepository;
  @Autowired IPrescriptionRepository prescriptionRepository;
  @Autowired IPaymentRepository paymentRepository;

  public AppointmentDTO createAppointment(AppointmentDTO appointment) {
    AppointmentEntity newAppointment = AppointmentMapper.INSTANCE.toEntity(appointment);
    newAppointment.setEmployee(employeeRepository.findById(appointment.getEmployeeUsername()).orElseThrow(() -> new RuntimeException("Employee not found")));
    newAppointment.setPatient(patientRepository.findById(appointment.getPatientUsername()).orElseThrow(() -> new RuntimeException("Patient not found")));
    appointmentRepository.save(newAppointment);
    return appointment;
  }

  public void deleteAppointment(int appointmentId) {
    appointmentRepository.deleteById(appointmentId);
  }

  public List<AppointmentDTO> getEmployeeAppointments(String employeeUsername) {
    return AppointmentMapper.INSTANCE.toDto(appointmentRepository.findAllByEmployeeUsername(employeeUsername));
  }

  public List<AppointmentDTO> getPatientAppointments(String patientUsername) {
    return AppointmentMapper.INSTANCE.toDto(appointmentRepository.findAllByPatientUsername(patientUsername));
  }

  public AppointmentDTO getAppointment(int appointmentId) {
    return AppointmentMapper.INSTANCE.toDto(appointmentRepository.findById(appointmentId).orElseThrow(() -> new RuntimeException("Appointment not found")));
  }

  // update appointment
  public AppointmentDTO updateAppointment(AppointmentDTO appointment) {
    AppointmentEntity newAppointment = AppointmentMapper.INSTANCE.toEntity(appointment);
    newAppointment.setEmployee(employeeRepository.findById(appointment.getEmployeeUsername()).orElseThrow(() -> new RuntimeException("Employee not found")));
    newAppointment.setPatient(patientRepository.findById(appointment.getPatientUsername()).orElseThrow(() -> new RuntimeException("Patient not found")));
    appointmentRepository.save(newAppointment);
    return appointment;
  }

  public List<PrescriptionDTO> getPrescriptions(int appointmentID) {
    return PrescriptionMapper.INSTANCE.toDto(prescriptionRepository.findAllByAppointmentId(appointmentID));
  }

  public List<PaymentDTO> getPayments(int appointmentID) {
    return PaymentMapper.INSTANCE.toDto(paymentRepository.findAllByAppointmentId(appointmentID));
  }
  
}
