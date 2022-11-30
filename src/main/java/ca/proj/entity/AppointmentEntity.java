package ca.proj.entity;

import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
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
  @Column(name = "appointmentid", unique = true)
  @GeneratedValue(strategy = GenerationType.AUTO)
  private int appointmentID;

  @Column(name = "appointmentdate")
  private Date appointmentDate;

  @Column(name = "appointmenttime")
  private String appointmentTime;

  @Column(name = "appointmentstatus")
  @Enumerated(EnumType.STRING)
  private AppointmentStatus appointmentStatus;

  @ManyToOne(cascade = CascadeType.PERSIST)
  @JoinColumn(referencedColumnName = "username", name = "employeeusername")
  private EmployeeEntity employee;

  @ManyToOne(cascade = CascadeType.PERSIST)
  @JoinColumn(referencedColumnName = "username", name = "patientusername")
  private PatientEntity patient;

  @OneToMany(mappedBy = "appointment")
  private Set<PrescriptionEntity> prescriptions = new HashSet<>();

  @OneToMany(mappedBy = "appointment")
  private Set<PaymentEntity> payments = new HashSet<>();
}
