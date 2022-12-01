package ca.proj.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ca.proj.dtos.PatientDTO;
import ca.proj.entity.PatientEntity;
import ca.proj.mapper.PatientMapper;
import ca.proj.repository.IAppointmentRepository;
import ca.proj.repository.IPatientRepository;
import ca.proj.repository.IUserRepository;

@Service
public class PatientService {
  @Autowired IPatientRepository patientRepository;
  @Autowired IUserRepository userRepository;
  @Autowired IAppointmentRepository appointmentRepository;

  public PatientDTO createPatient(PatientDTO patient) {
    PatientEntity newPatient = PatientMapper.INSTANCE.toEntity(patient);
    newPatient.setUser(userRepository.findById(patient.getUsername()).orElseThrow(() -> new RuntimeException("User not found"))
    );
    patientRepository.save(newPatient);
    return patient;
  }

  public List<String> getAllPatientsUsernames() {
    return patientRepository.findAllPatientsUsernames();
  }

  public void deletePatient(String patientUsername) {
    patientRepository.deleteById(patientUsername);
  }

  public PatientDTO getPatient(String patientUsername) {
    return PatientMapper.INSTANCE.toDto(patientRepository.findById(patientUsername).orElseThrow(() -> new RuntimeException("Patient not found")));
  }

  public List<PatientDTO> getDoctorsPatients(String doctorUsername) {
    // doctors patients are defined as patients that have appointments with the doctor
    List<String> patientUsernames = appointmentRepository.findPatientsByDoctorUsername(doctorUsername);
    return PatientMapper.INSTANCE.toDto(patientRepository.findAllById(patientUsernames));
  }
}
