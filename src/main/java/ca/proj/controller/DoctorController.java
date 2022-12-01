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
import ca.proj.dtos.PrescriptionDTO;
import ca.proj.service.AppointmentService;
import ca.proj.service.EmployeeService;
import ca.proj.service.PatientService;

@Controller
@RequestMapping("/doctor")
public class DoctorController {
  @Autowired private PatientService patientService;
  @Autowired private AppointmentService appointmentService;
  @Autowired private EmployeeService employeeService;

  @GetMapping("/")
  public String getDoctorDashboard(Model model, @AuthenticationPrincipal CustomUserDetails userDetails, Authentication authResult) {
    model.addAttribute("patients", patientService.getDoctorsPatients(userDetails.getUsername()));
    model.addAttribute("appointments", appointmentService.getDoctorsAppointments(userDetails.getUsername()));
    model.addAttribute("doctor", employeeService.getEmployee(userDetails.getUsername()));
    return Page.DOCTOR_DASHBOARD.getUrl();
  }

  @GetMapping("/viewPatient/{patientUsername}")
  public String viewPatient(Model model, @AuthenticationPrincipal CustomUserDetails userDetails, Authentication authResult, @PathVariable String patientUsername) {
    model.addAttribute("patient", patientService.getPatient(patientUsername));
    model.addAttribute("appointments", appointmentService.getPatientAppointments(patientUsername));
    return Page.VIEW_PATIENT.getUrl();
  }

  @GetMapping("/viewAppointment/{appointmentId}")
  public String viewAppointment(Model model, @AuthenticationPrincipal CustomUserDetails userDetails, Authentication authResult, @PathVariable int appointmentId) {
    model.addAttribute("appointment", appointmentService.getAppointment(appointmentId));
    model.addAttribute("prescriptions", appointmentService.getPrescriptions(appointmentId));
    model.addAttribute("payments", appointmentService.getPayments(appointmentId));
    model.addAttribute("doctorusername", userDetails.getUsername());
    return Page.VIEW_APPOINTMENT.getUrl();
  }

  @GetMapping("/add-prescription-page")
  public String addPrescriptionPage(Model model, @AuthenticationPrincipal CustomUserDetails userDetails, Authentication authResult) {
    model.addAttribute("prescriptionDTO", new PrescriptionDTO());
    model.addAttribute("appointments", appointmentService.getDoctorsAppointments(userDetails.getUsername()));
    return Page.DOCTOR_ADD_PRESCRIPTION.getUrl();
  }

  @GetMapping("/add-prescription-page-appointment/{appointmentId}")
  public String addPrescriptionPageAppointment(Model model, @AuthenticationPrincipal CustomUserDetails userDetails, Authentication authResult, @PathVariable int appointmentId) {
    model.addAttribute("appointments", appointmentService.getDoctorsAppointments(userDetails.getUsername()));
    model.addAttribute("id", appointmentId);
    model.addAttribute("prescriptionDTO", new PrescriptionDTO());
    return Page.DOCTOR_ADD_PRESCRIPTION.getUrl();
  }

  @PostMapping("/add-prescription")
  public String addPrescription(@Validated @ModelAttribute("prescriptionDTO") PrescriptionDTO prescriptionDTO, BindingResult result, Model model, @AuthenticationPrincipal CustomUserDetails userDetails, Authentication authResult) {
    boolean error = false;
    if(prescriptionDTO.getPrescriptionDate() == null) {
      model.addAttribute("nullDate", "Please select a date.");
      error = true;
    }
    else {
      LocalDate date = prescriptionDTO.getPrescriptionDate().toLocalDate();
      if(date.isBefore(LocalDate.now())) {
        model.addAttribute("pastDate", "Please select a date in the future.");
        error = true;
      }
    }
    if(prescriptionDTO.getPrescription().length() > 1000) {
      model.addAttribute("tooLong", "Prescription must be less than 1000 characters.");
      error = true;
    }
    if(prescriptionDTO.getPrescription().length() <= 5) {
      model.addAttribute("tooShort", "Prescription must be more than 5 characters.");
      error = true;
    }
    if(error) {
      model.addAttribute("prescriptionDTO", prescriptionDTO);
      model.addAttribute("appointments", appointmentService.getDoctorsAppointments(userDetails.getUsername()));
      return Page.DOCTOR_ADD_PRESCRIPTION.getUrl();
    }
    appointmentService.addPrescription(prescriptionDTO);

    model.addAttribute("addPrescriptionSuccess", true);
    // reset form
    model.addAttribute("prescriptionDTO", new PrescriptionDTO());
    model.addAttribute("appointments", appointmentService.getDoctorsAppointments(userDetails.getUsername()));
    return Page.DOCTOR_ADD_PRESCRIPTION.getUrl();
  }
}
