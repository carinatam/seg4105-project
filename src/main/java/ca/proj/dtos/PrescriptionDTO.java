package ca.proj.dtos;

import java.sql.Date;

import lombok.Data;

@Data
public class PrescriptionDTO {
  private int prescriptionID;
  private Date prescriptionDate;
  private String prescription;
  private int appointmentID;
}
