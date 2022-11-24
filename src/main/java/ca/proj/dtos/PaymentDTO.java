package ca.proj.dtos;

import java.sql.Date;
import java.sql.Time;

import lombok.Data;

@Data
public class PaymentDTO {
  private int paymentID;
  private Date paymentDate;
  private Time paymentTime;
  private float paymentAmount;
  private int appointmentID;
}
