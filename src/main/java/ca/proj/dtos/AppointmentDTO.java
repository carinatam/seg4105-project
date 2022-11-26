package ca.proj.dtos;

import java.sql.Date;

import ca.proj.values.AppointmentStatus;
import lombok.Data;

@Data
public class AppointmentDTO {
  private int appointmentID;
  private Date appointmentDate;
  private String appointmentTime;
  private AppointmentStatus appointmentStatus;
  private String employeeUsername;
  private String patientUsername;
}
