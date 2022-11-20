package ca.proj.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class MainController {

  @GetMapping("/")
  public String getAdminHomePage() {
    return Page.ADMIN_HOME.getUrl();
  }

  @GetMapping("/manage-employees")
  public String getManageEmployeesPage() {
    return Page.ADMIN_MANAGE_EMPLOYEES.getUrl();
  }

  @GetMapping("/view-patients")
  public String getViewPatientsPage() {
    return Page.ADMIN_VIEW_PATIENTS.getUrl();
  }

  @GetMapping("/manage-patients")
  public String getManagePatientsPage() {
    return Page.ADMIN_MANAGE_PATIENTS.getUrl();
  }

  @GetMapping("/logout")
  public String logout() {
    return Page.REDIRECT.getUrl() + Page.ADMIN_HOME.getUrl();
  }
}
