package ca.proj.service;

import java.math.BigInteger;

import org.springframework.beans.factory.annotation.Autowired;

import ca.proj.dtos.LoginUserDTO;
import ca.proj.repository.IEmployeeRepository;
import ca.proj.repository.IPatientRepository;
import ca.proj.repository.IUserRepository;

public class LoginUserService {
  @Autowired
  private IUserRepository userRepository;

  @Autowired
  private IPatientRepository patientRepository;

  @Autowired
  private IEmployeeRepository employeeRepository;

  public LoginUserDTO getUser(String username) {
    if(userRepository.loginExists(username) == BigInteger.ONE) {
      LoginUserDTO loginUser = new LoginUserDTO();
      loginUser.setUsername(username);
      loginUser.setPassword(userRepository.getPassword(username));
      // check if is patient
      if(patientRepository.isPatient(username) == BigInteger.ONE) {
        loginUser.setRole("PATIENT");
        return loginUser; 
      }
      else if(employeeRepository.isReceptionist(username) == BigInteger.ONE) {
        loginUser.setRole("RECEPTIONIST");
        return loginUser;
      }
      else if(employeeRepository.isDoctor(username) == BigInteger.ONE) {
        loginUser.setRole("DOCTOR");
        return loginUser;
      }
      else {
        loginUser.setRole("ADMIN");
        return loginUser;
      }
    }
    return null;
  }
}
