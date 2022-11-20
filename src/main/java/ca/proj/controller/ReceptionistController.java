package ca.proj.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/receptionist")
public class ReceptionistController {

  @GetMapping("/")
  public String getReceptionistDashboard() {
    return Page.RECEPTIONIST_DASHBOARD.getUrl();
  }
  
  @GetMapping("/add-appointment-page")
  public String getReceptionistAddAppointment() {
    return Page.RECEPTIONIST_ADD_APPOINTMENT.getUrl();
  }

  @GetMapping("/add-patient-page")
  public String getReceptionistAddPatient() {
    return Page.RECEPTIONIST_ADD_PATIENT.getUrl();
  }

  @GetMapping("/add-employee-page")
  public String getReceptionistAddEmployee() {
    return Page.RECEPTIONIST_ADD_EMPLOYEE.getUrl();
  }

}
