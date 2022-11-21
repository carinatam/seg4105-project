package ca.proj.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/doctor")
public class DoctorController {

  @GetMapping("/")
  public String getDoctorDashboard() {
    return Page.DOCTOR_DASHBOARD.getUrl();
  }
}
