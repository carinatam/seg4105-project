package ca.proj.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class AdminController {

  @GetMapping("/")
  public String getAdminDashboard() {
    return Page.ADMIN_DASHBOARD.getUrl();
  }
  
  @GetMapping("/add-employee-page")
  public String getAdminAddEmployee() {
    return Page.ADMIN_ADD_EMPLOYEE.getUrl();
  }

  @GetMapping("/add-patient-page")
  public String getAdminAddPatient() {
    return Page.ADMIN_ADD_PATIENT.getUrl();
  }

  @GetMapping("/add-appointment-page")
  public String getAdminAddAppointment() {
    return Page.ADMIN_ADD_APPOINTMENT.getUrl();
  }


}
