package ca.proj.dtos;

import java.sql.Date;

import lombok.Data;

@Data
public class PaymentDTO {
  private int paymentID;
  private Date paymentDate;
  private String paymentTime;
  private float paymentAmount;
  private int appointmentID;
}
