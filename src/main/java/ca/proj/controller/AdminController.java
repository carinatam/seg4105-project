package ca.proj.controller;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import ca.proj.config.security.CustomUserDetails;
import ca.proj.dtos.AppointmentDTO;
import ca.proj.dtos.EmployeeDTO;
import ca.proj.dtos.PatientDTO;
import ca.proj.dtos.UserDTO;
import ca.proj.service.AdminService;
import ca.proj.service.AppointmentService;
import ca.proj.service.EmployeeService;
import ca.proj.service.PatientService;
import ca.proj.service.UserService;

@Controller
@RequestMapping("/admin")
public class AdminController {

  @Autowired
  private AdminService adminService;
  @Autowired
  private UserService userService;
  @Autowired
  private EmployeeService employeeService;
  @Autowired
  private PatientService patientService;
  @Autowired
  private AppointmentService appointmentService;

  @GetMapping("/")
  public String getAdminDashboard(Model model, @AuthenticationPrincipal CustomUserDetails userDetails,
      Authentication authResult) {
    model.addAttribute("employees", adminService.getEmployees());
    model.addAttribute("patients", adminService.getPatients());
    model.addAttribute("appointments", adminService.getAppointments());
    model.addAttribute("payments", adminService.getPayments());
    return Page.ADMIN_DASHBOARD.getUrl();
  }

  @GetMapping("/add-employee-page")
  public String getAdminAddEmployee(Model model, @AuthenticationPrincipal CustomUserDetails userDetails,
      Authentication authResult) {
    model.addAttribute("userDTO", new UserDTO());
    model.addAttribute("employeeDTO", new EmployeeDTO());
    return Page.ADMIN_ADD_EMPLOYEE.getUrl();
  }

  @GetMapping("/add-patient-page")
  public String getAdminAddPatient(Model model, @AuthenticationPrincipal CustomUserDetails userDetails,
      Authentication authResult) {
    model.addAttribute("userDTO", new UserDTO());
    model.addAttribute("patientDTO", new PatientDTO());
    return Page.ADMIN_ADD_PATIENT.getUrl();
  }

  @GetMapping("/appointments/{appointmentID}")
  public String getAdminViewAppointment(Model model, @AuthenticationPrincipal CustomUserDetails userDetails,
      Authentication authResult, @PathVariable int appointmentID) {
    model.addAttribute("appointment", appointmentService.getAppointment(appointmentID));
    model.addAttribute("prescriptions", appointmentService.getPrescriptions(appointmentID));
    model.addAttribute("payments", appointmentService.getPayments(appointmentID));
    return Page.ADMIN_VIEW_APPOINTMENT.getUrl();
  }

  @GetMapping("/add-appointment-page")
  public String getAdminAddAppointment(Model model, @AuthenticationPrincipal CustomUserDetails userDetails,
      Authentication authResult) {
    model.addAttribute("appointmentDTO", new AppointmentDTO());
    model.addAttribute("employeeUsernames", employeeService.getAllDoctorsUsernames());
    model.addAttribute("patientUsernames", patientService.getAllPatientsUsernames());
    return Page.ADMIN_ADD_APPOINTMENT.getUrl();
  }

  @GetMapping("/add-appointment-page-employee/{employeeUsername}")
  public String getAdminAddAppointmentEmployee(Model model, @AuthenticationPrincipal CustomUserDetails userDetails,
      Authentication authResult, @PathVariable String employeeUsername) {
    AppointmentDTO appointmentDTO = new AppointmentDTO();
    appointmentDTO.setEmployeeUsername(employeeUsername);
    model.addAttribute("appointmentDTO", appointmentDTO);
    model.addAttribute("employeeUsernames", employeeService.getAllDoctorsUsernames());
    model.addAttribute("patientUsernames", patientService.getAllPatientsUsernames());
    return Page.ADMIN_ADD_APPOINTMENT.getUrl();
  }

  @GetMapping("/add-appointment-page-patient/{patientUsername}")
  public String getAdminAddAppointmentPatient(Model model, @AuthenticationPrincipal CustomUserDetails userDetails,
      Authentication authResult, @PathVariable String patientUsername) {
    AppointmentDTO appointmentDTO = new AppointmentDTO();
    appointmentDTO.setPatientUsername(patientUsername);
    model.addAttribute("appointmentDTO", appointmentDTO);
    model.addAttribute("employeeUsernames", employeeService.getAllDoctorsUsernames());
    model.addAttribute("patientUsernames", patientService.getAllPatientsUsernames());
    return Page.ADMIN_ADD_APPOINTMENT.getUrl();
  }

  @GetMapping("/viewEmployee/{username}")
  public String getAdminViewEmployee(Model model, @AuthenticationPrincipal CustomUserDetails userDetails,
      Authentication authResult, @PathVariable String username) {
    model.addAttribute("employee", employeeService.getEmployee(username));
    model.addAttribute("appointments", appointmentService.getEmployeeAppointments(username));
    return Page.ADMIN_VIEW_EMPLOYEE.getUrl();
  }

  @GetMapping("/viewPatient/{username}")
  public String getAdminViewPatient(Model model, @AuthenticationPrincipal CustomUserDetails userDetails,
      Authentication authResult, @PathVariable String username) {
    model.addAttribute("patient", patientService.getPatient(username));
    model.addAttribute("appointments", appointmentService.getPatientAppointments(username));
    return Page.ADMIN_VIEW_PATIENT.getUrl();
  }

  @GetMapping("/edit-appointment-page/{appointmentID}")
  public String getAdminEditAppointment(Model model, @AuthenticationPrincipal CustomUserDetails userDetails,
      Authentication authResult, @PathVariable int appointmentID) {
    model.addAttribute("appointmentDTO", appointmentService.getAppointment(appointmentID));
    model.addAttribute("employeeUsernames", employeeService.getAllDoctorsUsernames());
    model.addAttribute("patientUsernames", patientService.getAllPatientsUsernames());
    System.out.println("appointmentDTO: " + appointmentService.getAppointment(appointmentID));
    return Page.ADMIN_EDIT_APPOINTMENT.getUrl();
  }

  @PostMapping("/editAppointment")
  public String editAppointment(@ModelAttribute("appointmentDTO") @Validated AppointmentDTO appointmentDTO,
      BindingResult bindingResult, Model model, @AuthenticationPrincipal CustomUserDetails userDetails) {
    boolean error = false;
    if (appointmentDTO.getAppointmentDate() == null) {
      model.addAttribute("nullDate", "Please select a date.");
      error = true;
    } else {
      LocalDate date = appointmentDTO.getAppointmentDate().toLocalDate();
      if (date.isBefore(LocalDate.now())) {
        model.addAttribute("pastDate", "Please select a date in the future.");
        error = true;
      }
    }
    if (appointmentDTO.getAppointmentTime() == null) {
      model.addAttribute("nullTime", "Please select a time.");
      error = true;
    }
    if (error) {
      model.addAttribute("appointment", appointmentDTO);
      return Page.ADMIN_EDIT_APPOINTMENT.getUrl();
    }
    appointmentService.updateAppointment(appointmentDTO);
    model.addAttribute("employee", employeeService.getEmployee(appointmentDTO.getEmployeeUsername()));
    model.addAttribute("appointments", appointmentService.getEmployeeAppointments(appointmentDTO.getEmployeeUsername()));
    return Page.ADMIN_VIEW_EMPLOYEE.getUrl();
  }

  @PostMapping("/add-employee")
  public String addEmployee(@Validated @ModelAttribute("userDTO") UserDTO userDTO,
      @Validated @ModelAttribute("employeeDTO") EmployeeDTO employeeDTO, BindingResult result, Model model) {
    boolean error = false;
    if (userDTO.getUsername().isBlank()) {
      model.addAttribute("nullUsername", "Please enter a username.");
      error = true;
    } else if (userService.userExists(userDTO.getUsername())) {
      model.addAttribute("usernameExists", "This username already exists.");
      error = true;
    }
    if (userDTO.getPassword().isBlank()) {
      model.addAttribute("nullPassword", "Please enter a password.");
      error = true;
    }
    if (employeeDTO.getFirstName().isBlank()) {
      model.addAttribute("nullFirstName", "Please enter a first name.");
      error = true;
    }
    if (employeeDTO.getLastName().isBlank()) {
      model.addAttribute("nullLastName", "Please enter a last name.");
      error = true;
    }
    if (employeeDTO.getDateOfBirth() == null) {
      model.addAttribute("nullDateOfBirth", "Please enter a date of birth.");
      error = true;
    }
    if (employeeDTO.getAddress().isBlank()) {
      model.addAttribute("nullAddress", "Please enter an address.");
      error = true;
    }
    if (employeeDTO.getPhone().isBlank()) {
      model.addAttribute("nullPhone", "Please enter a phone number.");
      error = true;
    }
    if (employeeDTO.getEmail().isBlank()) {
      model.addAttribute("nullEmail", "Please enter an email.");
      error = true;
    }
    if (employeeDTO.getSalary() <= 0) {
      model.addAttribute("nullSalary", "Please enter a valid salary.");
      error = true;
    }

    if (error) {
      model.addAttribute("userDTO", userDTO);
      model.addAttribute("employeeDTO", employeeDTO);
      return Page.ADMIN_ADD_EMPLOYEE.getUrl();
    }
    userService.createUser(userDTO);
    employeeDTO.setUsername(userDTO.getUsername());
    employeeService.createEmployee(employeeDTO);

    model.addAttribute("addEmployeeSuccess", true);
    model.addAttribute("userDTO", new UserDTO());
    model.addAttribute("employeeDTO", new EmployeeDTO());
    return Page.ADMIN_ADD_EMPLOYEE.getUrl();
  }

  @PostMapping("/add-patient")
  public String addPatient(@Validated @ModelAttribute("userDTO") UserDTO userDTO,
      @Validated @ModelAttribute("patientDTO") PatientDTO patientDTO, BindingResult result, Model model) {
    boolean error = false;
    if (userDTO.getUsername().isBlank()) {
      model.addAttribute("nullUsername", "Please enter a username.");
      error = true;
    } else if (userService.userExists(userDTO.getUsername())) {
      model.addAttribute("usernameExists", "This username already exists.");
      error = true;
    }
    if (userDTO.getPassword().isBlank()) {
      model.addAttribute("nullPassword", "Please enter a password.");
      error = true;
    }
    if (patientDTO.getFirstName().isBlank()) {
      model.addAttribute("nullFirstName", "Please enter a first name.");
      error = true;
    }
    if (patientDTO.getLastName().isBlank()) {
      model.addAttribute("nullLastName", "Please enter a last name.");
      error = true;
    }
    if (patientDTO.getDateOfBirth() == null) {
      model.addAttribute("nullDateOfBirth", "Please enter a date of birth.");
      error = true;
    }
    if (patientDTO.getAddress().isBlank()) {
      model.addAttribute("nullAddress", "Please enter an address.");
      error = true;
    }
    if (patientDTO.getPhone().isBlank()) {
      model.addAttribute("nullPhone", "Please enter a phone number.");
      error = true;
    }
    if (patientDTO.getEmail().isBlank()) {
      model.addAttribute("nullEmail", "Please enter an email.");
      error = true;
    }

    if (error) {
      model.addAttribute("userDTO", userDTO);
      model.addAttribute("patientDTO", patientDTO);
      return Page.ADMIN_ADD_PATIENT.getUrl();
    }
    userService.createUser(userDTO);
    patientDTO.setUsername(userDTO.getUsername());
    patientService.createPatient(patientDTO);

    model.addAttribute("addPatientSuccess", true);
    // reset form
    model.addAttribute("userDTO", new UserDTO());
    model.addAttribute("patientDTO", new PatientDTO());
    return Page.ADMIN_ADD_PATIENT.getUrl();
  }

  @PostMapping("/add-appointment")
  public String addAppointment(@Validated @ModelAttribute("appointmentDTO") AppointmentDTO appointmentDTO,
      BindingResult result, Model model) {
    boolean error = false;
    if (appointmentDTO.getAppointmentDate() == null) {
      model.addAttribute("nullDate", "Please select a date.");
      error = true;
    } else {
      LocalDate date = appointmentDTO.getAppointmentDate().toLocalDate();
      if (date.isBefore(LocalDate.now())) {
        model.addAttribute("pastDate", "Please select a date in the future.");
        error = true;
      }
    }

    if (appointmentDTO.getAppointmentTime() == null) {
      model.addAttribute("nullTime", "Please select a time.");
      error = true;
    }

    if (error) {
      model.addAttribute("appointmentDTO", appointmentDTO);
      model.addAttribute("employeeUsernames", employeeService.getAllDoctorsUsernames());
      model.addAttribute("patientUsernames", patientService.getAllPatientsUsernames());
      return Page.ADMIN_ADD_APPOINTMENT.getUrl();
    }
    appointmentService.createAppointment(appointmentDTO);

    model.addAttribute("addAppointmentSuccess", true);
    // reset form
    model.addAttribute("appointmentDTO", new AppointmentDTO());
    model.addAttribute("employeeUsernames", employeeService.getAllDoctorsUsernames());
    model.addAttribute("patientUsernames", patientService.getAllPatientsUsernames());
    return Page.ADMIN_ADD_APPOINTMENT.getUrl();
  }

  @PostMapping("/deleteEmployee/{employeeUsername}")
  public String deleteEmployee(@PathVariable("employeeUsername") String employeeUsername,
      @AuthenticationPrincipal CustomUserDetails userDetails, Model model) {
    employeeService.deleteEmployee(employeeUsername);
    model.addAttribute("employees", adminService.getEmployees());
    model.addAttribute("patients", adminService.getPatients());
    model.addAttribute("appointments", adminService.getAppointments());
    model.addAttribute("payments", adminService.getPayments());
    return Page.ADMIN_DASHBOARD.getUrl();
  }

  @PostMapping("/deletePatient/{patientUsername}")
  public String deletePatient(@PathVariable("patientUsername") String patientUsername,
      @AuthenticationPrincipal CustomUserDetails userDetails, Model model) {
    patientService.deletePatient(patientUsername);
    model.addAttribute("employees", adminService.getEmployees());
    model.addAttribute("patients", adminService.getPatients());
    model.addAttribute("appointments", adminService.getAppointments());
    model.addAttribute("payments", adminService.getPayments());
    return Page.ADMIN_DASHBOARD.getUrl();
  }

  @PostMapping("/deleteAppointment/{appointmentId}")
  public String deleteAppointment(@PathVariable("appointmentId") int appointmentId,
      @AuthenticationPrincipal CustomUserDetails userDetails, Model model) {
    appointmentService.deleteAppointment(appointmentId);
    model.addAttribute("employees", adminService.getEmployees());
    model.addAttribute("patients", adminService.getPatients());
    model.addAttribute("appointments", adminService.getAppointments());
    model.addAttribute("payments", adminService.getPayments());
    return Page.ADMIN_DASHBOARD.getUrl();
  }
}
