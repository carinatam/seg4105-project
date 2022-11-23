package ca.proj.dtos;

import java.sql.Date;
import java.sql.Time;

import lombok.Data;

@Data
public class AppointmentDTO {
  private int appointmentID;
  private Date appointmentDate;
  private Time appointmentTime;
  private String employeeUsername;
  private String patientUsername;
}
