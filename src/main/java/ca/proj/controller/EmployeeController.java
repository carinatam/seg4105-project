package ca.proj.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import ca.proj.config.security.CustomUserDetails;
import ca.proj.service.AdminService;
import ca.proj.service.EmployeeService;

@Controller
@RequestMapping("/employee")
public class EmployeeController {

  @Autowired
  EmployeeService employeeService;
  @Autowired
  AdminService adminService;

  

}
