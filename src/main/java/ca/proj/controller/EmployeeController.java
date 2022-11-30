package ca.proj.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

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
