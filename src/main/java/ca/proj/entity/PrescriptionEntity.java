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
@Table(name = "prescription")
@Data
public class PrescriptionEntity {
  @Id
  @Column(name = "prescriptionID")
  private int prescriptionID;

  @Column(name = "prescriptionDate")
  private Date prescriptionDate;

  @Column(name = "prescription")
  private String prescription;

  @ManyToOne
  @JoinColumn(name = "appointmentID")
  private AppointmentEntity appointment;
}
