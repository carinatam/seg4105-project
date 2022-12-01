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
import ca.proj.service.ReceptionistService;
import ca.proj.service.UserService;

@Controller
@RequestMapping("/receptionist")
public class ReceptionistController {
  @Autowired
  private UserService userService;
  @Autowired
  private AdminService adminService;
  @Autowired
  private ReceptionistService receptionistService;
  @Autowired
  private EmployeeService employeeService;
  @Autowired
  private AppointmentService appointmentService;
  @Autowired
  private PatientService patientService;

  @GetMapping("/")
  public String getReceptionistDashboard(Model model, @AuthenticationPrincipal CustomUserDetails userDetails,
      Authentication authResult) {
    model.addAttribute("employees", receptionistService.getEmployeesNoAdmin());
    model.addAttribute("patients", adminService.getPatients());
    model.addAttribute("appointments", adminService.getAppointments());
    model.addAttribute("payments", adminService.getPayments());
    return Page.RECEPTIONIST_DASHBOARD.getUrl();
  }

  @GetMapping("/add-appointment-page")
  public String getReceptionistAddAppointment(Model model, @AuthenticationPrincipal CustomUserDetails userDetails,
      Authentication authResult) {
    model.addAttribute("appointmentDTO", new AppointmentDTO());
    model.addAttribute("employeeUsernames", employeeService.getAllDoctorsUsernames());
    model.addAttribute("patientUsernames", patientService.getAllPatientsUsernames());
    return Page.ADD_APPOINTMENT.getUrl();
  }

  @GetMapping("/add-patient-page")
  public String getReceptionistAddPatient(Model model, @AuthenticationPrincipal CustomUserDetails userDetails,
  Authentication authResult) {
    model.addAttribute("userDTO", new UserDTO());
    model.addAttribute("patientDTO", new PatientDTO());
    return Page.ADD_PATIENT.getUrl();
  }

  @GetMapping("/add-employee-page")
  public String getReceptionistAddEmployee(Model model, @AuthenticationPrincipal CustomUserDetails userDetails,
  Authentication authResult) {
    model.addAttribute("userDTO", new UserDTO());
    model.addAttribute("employeeDTO", new EmployeeDTO());
    return Page.ADD_EMPLOYEE.getUrl();
  }

  @GetMapping("/viewEmployee/{username}")
  public String getReceptionistViewEmployee(Model model, @PathVariable String username,
      @AuthenticationPrincipal CustomUserDetails userDetails, Authentication authResult) {
    model.addAttribute("employee", employeeService.getEmployee(username));
    model.addAttribute("appointments", appointmentService.getEmployeeAppointments(username));
    return Page.VIEW_EMPLOYEE.getUrl();
  }

  @GetMapping("/viewPatient/{username}")
  public String getReceptionistViewPatient(Model model, @PathVariable String username,
      @AuthenticationPrincipal CustomUserDetails userDetails, Authentication authResult) {
    model.addAttribute("patient", patientService.getPatient(username));
    model.addAttribute("appointments", appointmentService.getPatientAppointments(username));
    return Page.VIEW_PATIENT.getUrl();
  }

  @GetMapping("/appointments/{id}")
  public String getReceptionistViewAppointment(Model model, @PathVariable int id,
      @AuthenticationPrincipal CustomUserDetails userDetails, Authentication authResult) {
    model.addAttribute("appointment", appointmentService.getAppointment(id));
    model.addAttribute("prescriptions", appointmentService.getPrescriptions(id));
    model.addAttribute("payments", appointmentService.getPayments(id));
    return Page.VIEW_APPOINTMENT.getUrl();
  }

  @GetMapping("/edit-appointment-page/{id}")
  public String getReceptionistEditAppointment(Model model, @PathVariable int id,
      @AuthenticationPrincipal CustomUserDetails userDetails, Authentication authResult) {
    model.addAttribute("appointmentDTO", appointmentService.getAppointment(id));
    model.addAttribute("employeeUsernames", employeeService.getAllDoctorsUsernames());
    model.addAttribute("patientUsernames", patientService.getAllPatientsUsernames());
    return Page.EDIT_APPOINTMENT.getUrl();
  }

  @GetMapping("/add-appointment-page-employee/{username}")
  public String getReceptionistAddAppointmentEmployee(Model model, @PathVariable String username,
      @AuthenticationPrincipal CustomUserDetails userDetails, Authentication authResult) {
    AppointmentDTO appointment = new AppointmentDTO();
    appointment.setEmployeeUsername(username);
    model.addAttribute("appointmentDTO", appointment);
    model.addAttribute("employeeUsernames", employeeService.getAllDoctorsUsernames());
    model.addAttribute("patientUsernames", patientService.getAllPatientsUsernames());
    return Page.ADD_APPOINTMENT.getUrl();
  }

  @GetMapping("/add-appointment-page-patient/{username}")
  public String getReceptionistAddAppointmentPatient(Model model, @PathVariable String username,
      @AuthenticationPrincipal CustomUserDetails userDetails, Authentication authResult) {
    AppointmentDTO appointment = new AppointmentDTO();
    appointment.setPatientUsername(username);
    model.addAttribute("appointmentDTO", appointment);
    model.addAttribute("employeeUsernames", employeeService.getAllDoctorsUsernames());
    model.addAttribute("patientUsernames", patientService.getAllPatientsUsernames());
    return Page.ADD_APPOINTMENT.getUrl();
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

    if (appointmentDTO.getAppointmentTime() == "") {
      model.addAttribute("nullTime", "Please select a time.");
      error = true;
    }

    if (error) {
      model.addAttribute("appointmentDTO", appointmentDTO);
      model.addAttribute("employeeUsernames", employeeService.getAllDoctorsUsernames());
      model.addAttribute("patientUsernames", patientService.getAllPatientsUsernames());
      return Page.ADD_APPOINTMENT.getUrl();
    }
    appointmentService.createAppointment(appointmentDTO);

    model.addAttribute("addAppointmentSuccess", true);
    // reset form
    model.addAttribute("appointmentDTO", new AppointmentDTO());
    model.addAttribute("employeeUsernames", employeeService.getAllDoctorsUsernames());
    model.addAttribute("patientUsernames", patientService.getAllPatientsUsernames());
    return Page.ADD_APPOINTMENT.getUrl();
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
      return Page.ADD_EMPLOYEE.getUrl();
    }
    userService.createUser(userDTO);
    employeeDTO.setUsername(userDTO.getUsername());
    employeeService.createEmployee(employeeDTO);

    model.addAttribute("addEmployeeSuccess", true);
    model.addAttribute("userDTO", new UserDTO());
    model.addAttribute("employeeDTO", new EmployeeDTO());
    return Page.ADD_EMPLOYEE.getUrl();
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
      return Page.ADD_PATIENT.getUrl();
    }
    userService.createUser(userDTO);
    patientDTO.setUsername(userDTO.getUsername());
    patientService.createPatient(patientDTO);

    model.addAttribute("addPatientSuccess", true);
    // reset form
    model.addAttribute("userDTO", new UserDTO());
    model.addAttribute("patientDTO", new PatientDTO());
    return Page.ADD_PATIENT.getUrl();
  }

  @PostMapping("/deleteEmployee/{username}")
  public String postReceptionistDeleteEmployee(@PathVariable String username,
      @AuthenticationPrincipal CustomUserDetails userDetails, Authentication authResult, Model model) {
    employeeService.deleteEmployee(username);
    model.addAttribute("employees", receptionistService.getEmployeesNoAdmin());
    model.addAttribute("patients", adminService.getPatients());
    model.addAttribute("appointments", adminService.getAppointments());
    model.addAttribute("payments", adminService.getPayments());
    return Page.RECEPTIONIST_DASHBOARD.getUrl();
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
      return Page.EDIT_APPOINTMENT.getUrl();
    }
    appointmentService.updateAppointment(appointmentDTO);
    model.addAttribute("employee", employeeService.getEmployee(appointmentDTO.getEmployeeUsername()));
    model.addAttribute("appointments",
        appointmentService.getEmployeeAppointments(appointmentDTO.getEmployeeUsername()));
    return Page.VIEW_EMPLOYEE.getUrl();
  }

  @PostMapping("/deleteAppointment/{id}")
  public String postReceptionistDeleteAppointment(@PathVariable int id,
      @AuthenticationPrincipal CustomUserDetails userDetails, Authentication authResult, Model model) {
    appointmentService.deleteAppointment(id);
    model.addAttribute("employees", adminService.getEmployees());
    model.addAttribute("patients", adminService.getPatients());
    model.addAttribute("appointments", adminService.getAppointments());
    model.addAttribute("payments", adminService.getPayments());
    return Page.ADMIN_DASHBOARD.getUrl();
  }

}
