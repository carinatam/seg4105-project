package ca.proj.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import ca.proj.service.AdminService;
import ca.proj.service.PatientService;

@Controller
@RequestMapping("/patient")
public class PatientController {

  @Autowired PatientService patientService;
  @Autowired AdminService adminService;
  
  @GetMapping("/")
  public String getPatientDashboard() {
    return Page.PATIENT_DASHBOARD.getUrl();
  }

  @GetMapping("/book-appointment")
  public String getPatientAddAppointment() {
    return Page.PATIENT_ADD_APPOINTMENT.getUrl();
  }
}
