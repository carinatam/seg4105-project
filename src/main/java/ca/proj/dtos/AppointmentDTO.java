package ca.proj.dtos;

import java.sql.Date;
import java.sql.Time;

import ca.proj.values.AppointmentStatus;
import lombok.Data;

@Data
public class AppointmentDTO {
  private int appointmentID;
  private Date appointmentDate;
  private Time appointmentTime;
  private AppointmentStatus appointmentStatus;
  private String employeeUsername;
  private String patientUsername;
}
