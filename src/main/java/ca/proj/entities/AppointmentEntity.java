package ca.proj.entities;

import java.sql.Date;
import java.sql.Time;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "appointment")
@Data
public class AppointmentEntity {
  @Id
  @Column(name = "appointmentID")
  private int appointmentID;

  @Column(name = "appointmentDate")
  private Date appointmentDate;

  @Column(name = "appointmentTime")
  private Time appointmentTime;

  @Column(name = "appointmentStatus")
  private AppointmentStatus appointmentStatus;

  @ManyToOne
  @JoinColumn(name = "employeeUsername")
  private EmployeeEntity employee;

  @ManyToOne
  @JoinColumn(name = "patientUsername")
  private PatientEntity patient;

  @OneToMany(mappedBy = "appointment")
  private Set<PrescriptionEntity> prescriptions;

  @OneToMany(mappedBy = "appointment")
  private Set<PaymentEntity> payments;
}
