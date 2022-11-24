package ca.proj.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import ca.proj.config.security.CustomUserDetails;
import ca.proj.service.AdminService;

@Controller
@RequestMapping("/admin")
public class AdminController {

  @Autowired private AdminService adminService;

  @GetMapping("/")
  public String getAdminDashboard(Model model, @AuthenticationPrincipal CustomUserDetails userDetails, Authentication authResult)  {
    model.addAttribute("employees", adminService.getEmployees());
    model.addAttribute("patients", adminService.getPatients());
    model.addAttribute("appointments", adminService.getAppointments());
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
