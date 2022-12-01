package ca.proj.controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
import ca.proj.dtos.PaymentDTO;
import ca.proj.service.AppointmentService;
import ca.proj.service.EmployeeService;
import ca.proj.service.PatientService;
import ca.proj.values.AppointmentStatus;

@Controller
@RequestMapping("/patient")
public class PatientController {

  @Autowired
  private AppointmentService appointmentService;
  @Autowired
  private PatientService patientService;
  @Autowired private EmployeeService employeeService;

  @GetMapping("/")
  public String getPatientDashboard(Model model, @AuthenticationPrincipal CustomUserDetails userDetails,
      Authentication authResult) {
    List<AppointmentDTO> appointments = appointmentService.getPatientAppointments(userDetails.getUsername());
    List<PaymentDTO> payments = new ArrayList<PaymentDTO>();
    for (AppointmentDTO appointment : appointments) {
      payments.addAll(appointmentService.getPayments(appointment.getAppointmentID()));
    }
    model.addAttribute("appointments", appointments);
    model.addAttribute("payments", payments);
    model.addAttribute("patient", patientService.getPatient(userDetails.getUsername()));
    return Page.PATIENT_DASHBOARD.getUrl();
  }

  @GetMapping("/viewAppointment/{appointmentID}")
  public String viewAppointment(Model model, @AuthenticationPrincipal CustomUserDetails userDetails,
      Authentication authResult, @PathVariable int appointmentID) {
    model.addAttribute("appointment", appointmentService.getAppointment(appointmentID));
    model.addAttribute("prescriptions", appointmentService.getPrescriptions(appointmentID));
    model.addAttribute("payments", appointmentService.getPayments(appointmentID));
    return Page.VIEW_APPOINTMENT.getUrl();
  }

  @GetMapping("/make-payment")
  public String makePayment(Model model, @AuthenticationPrincipal CustomUserDetails userDetails,
      Authentication authResult) {
    model.addAttribute("payment", new PaymentDTO());
    model.addAttribute("appointments", appointmentService.getPatientAppointments(userDetails.getUsername()));
    return Page.PATIENT_MAKE_PAYMENT.getUrl();
  }

  @GetMapping("/make-payment-page-appointment/{appointmentID}")
  public String makePaymentPageAppointment(Model model, @AuthenticationPrincipal CustomUserDetails userDetails,
      Authentication authResult, @PathVariable int appointmentID) {
    model.addAttribute("appointments", appointmentService.getPatientAppointments(userDetails.getUsername()));
    model.addAttribute("id", appointmentID);
    PaymentDTO payment = new PaymentDTO();
    payment.setAppointmentID(appointmentID);
    model.addAttribute("payment", payment);
    return Page.PATIENT_MAKE_PAYMENT.getUrl();
  }

  @GetMapping("/book-appointment")
  public String getPatientAddAppointment(Model model, @AuthenticationPrincipal CustomUserDetails userDetails,
      Authentication authResult) {
    AppointmentDTO appointment = new AppointmentDTO();
    model.addAttribute("appointmentDTO", appointment);
    model.addAttribute("employeeUsernames", employeeService.getAllDoctorsUsernames());
    model.addAttribute("patientUsernames", Arrays.asList(userDetails.getUsername()));
    return Page.ADD_APPOINTMENT.getUrl();
  }

  @PostMapping("/make-payment")
  public String makePayment(@Validated @ModelAttribute("payment") PaymentDTO payment, BindingResult result, Model model,
      @AuthenticationPrincipal CustomUserDetails userDetails, Authentication authResult) {
    boolean error = false;
    if (payment.getPaymentDate() == null) {
      model.addAttribute("nullDate", "Please select a date.");
      error = true;
    } else {
      LocalDate date = payment.getPaymentDate().toLocalDate();
      if (date.isBefore(LocalDate.now())) {
        model.addAttribute("pastDate", "Please select a date in the future.");
        error = true;
      }
    }
    if (payment.getPaymentAmount() == 0) {
      model.addAttribute("nullAmount", "Please enter an amount.");
      error = true;
    }
    if (error) {
      model.addAttribute("appointments", appointmentService.getPatientAppointments(userDetails.getUsername()));
      model.addAttribute("payment", payment);
      return Page.PATIENT_MAKE_PAYMENT.getUrl();
    }
    appointmentService.addPayment(payment);

    model.addAttribute("payment", new PaymentDTO());
    model.addAttribute("appointments", appointmentService.getPatientAppointments(userDetails.getUsername()));
    model.addAttribute("addPaymentSuccess", true);
    return Page.PATIENT_MAKE_PAYMENT.getUrl();
  }

  @PostMapping("/add-appointment")
  public String addAppointment(@Validated @ModelAttribute("appointmentDTO") AppointmentDTO appointment,
      BindingResult result, Model model, @AuthenticationPrincipal CustomUserDetails userDetails,
      Authentication authResult) {
    System.out.println("appointment: " + appointment);
    boolean error = false;
    if (appointment.getAppointmentDate() == null) {
      model.addAttribute("nullDate", "Please select a date.");
      error = true;
    } else {
      LocalDate date = appointment.getAppointmentDate().toLocalDate();
      if (date.isBefore(LocalDate.now())) {
        model.addAttribute("pastDate", "Please select a date in the future.");
        error = true;
      }
    }
    if(appointment.getAppointmentTime() == "") {
      model.addAttribute("nullTime", "Please select a time.");
      error = true;
    }
    if (error) {
      model.addAttribute("appointmentDTO", appointment);
      model.addAttribute("employeeUsernames", employeeService.getAllDoctorsUsernames());
      model.addAttribute("patientUsernames", Arrays.asList(userDetails.getUsername()));
      return Page.ADD_APPOINTMENT.getUrl();
    }
    appointment.setPatientUsername(userDetails.getUsername());
    appointment.setAppointmentStatus(AppointmentStatus.Pending);
    appointmentService.createAppointment(appointment);
    model.addAttribute("appointmentDTO", new AppointmentDTO());
    model.addAttribute("employeeUsernames", employeeService.getAllDoctorsUsernames());
    model.addAttribute("patientUsernames", Arrays.asList(userDetails.getUsername()));
    model.addAttribute("addAppointmentSuccess", true);
    return Page.ADD_APPOINTMENT.getUrl();
  }
}
