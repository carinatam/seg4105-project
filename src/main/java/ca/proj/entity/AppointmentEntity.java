package ca.proj.entity;

import java.sql.Date;
import java.sql.Time;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import ca.proj.values.AppointmentStatus;
import lombok.Data;

@Entity
@Table(name = "appointment")
@Data
public class AppointmentEntity {
  @Id
  @Column(name = "appointmentid")
  private int appointmentID;

  @Column(name = "appointmentdate")
  private Date appointmentDate;

  @Column(name = "appointmenttime")
  private Time appointmentTime;

  @Column(name = "appointmentstatus")
  @Enumerated(EnumType.STRING)
  private AppointmentStatus appointmentStatus;

  @ManyToOne
  @JoinColumn(name = "employeeusername")
  private EmployeeEntity employee;

  @ManyToOne
  @JoinColumn(name = "patientusername")
  private PatientEntity patient;

  @OneToMany(mappedBy = "appointment")
  private Set<PrescriptionEntity> prescriptions;

  @OneToMany(mappedBy = "appointment")
  private Set<PaymentEntity> payments;
}
