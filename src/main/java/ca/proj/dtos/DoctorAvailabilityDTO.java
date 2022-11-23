package ca.proj.dtos;

import java.sql.Time;

import lombok.Data;

@Data
public class DoctorAvailabilityDTO {
  private String day;
  private Time startTime;
  private Time endTime;
  private String doctorUsername;
}
