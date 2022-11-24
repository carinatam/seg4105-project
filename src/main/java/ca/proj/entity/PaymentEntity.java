package ca.proj.entity;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "payment")
@Data
public class PaymentEntity {
  @Id
  @Column(name = "paymentID")
  private int paymentID;

  @Column(name = "paymentDate")
  private Date paymentDate;

  @Column(name = "paymentAmount")
  private double paymentAmount;

  @ManyToOne
  @JoinColumn(name = "appointmentID")
  private AppointmentEntity appointment;
}
