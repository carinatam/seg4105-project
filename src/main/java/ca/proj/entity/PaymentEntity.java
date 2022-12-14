package ca.proj.entity;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
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
  @Column(name = "paymentid")
  @GeneratedValue(strategy = GenerationType.AUTO)
  private int paymentID;

  @Column(name = "paymentdate")
  private Date paymentDate;

  @Column(name = "paymentamount")
  private double paymentAmount;

  @Column(name = "paymenttime")
  private String paymentTime;

  @ManyToOne
  @JoinColumn(name = "appointmentid")
  private AppointmentEntity appointment;
}
