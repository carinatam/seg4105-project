package ca.proj.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainController {
  
  @GetMapping("/")
  public String getLoginPage() {
    return Page.LOGIN.getUrl();
  }

  @GetMapping("/login")
  public String getLoginPageRedirect() {
    return Page.LOGIN.getUrl();
  }

  @RequestMapping("/loginSuccess")
  public void loginPageRedirect(HttpServletRequest request, HttpServletResponse response, Authentication authResult) throws ServletException, IOException {
    String role = authResult.getAuthorities().toString();
    if(role.contains("ADMIN")) {
      response.sendRedirect("/admin/");
    } else if(role.contains("DOCTOR")) {
      response.sendRedirect("/doctor/");
    } else if(role.contains("RECEPTIONIST")) {
      response.sendRedirect("/receptionist/");
    } else if(role.contains("PATIENT")) {
      response.sendRedirect("/patient/");
    }
  }

  @RequestMapping("/login-error")
  public String loginError(Model model) {
    return Page.LOGIN.getUrl();
  }
}
