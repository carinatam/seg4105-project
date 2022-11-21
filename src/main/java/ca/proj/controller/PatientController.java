package ca.proj.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/patient")
public class PatientController {
  
  @GetMapping("/")
  public String getPatientDashboard() {
    return Page.PATIENT_DASHBOARD.getUrl();
  }

  @GetMapping("/book-appointment")
  public String getPatientAddAppointment() {
    return Page.PATIENT_ADD_APPOINTMENT.getUrl();
  }
}
