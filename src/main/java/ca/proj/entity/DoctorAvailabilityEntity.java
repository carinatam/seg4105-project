package ca.proj.entity;

import java.sql.Time;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "doctoravailability")
@Data
public class DoctorAvailabilityEntity {
  @Id
  @Column(name = "rowid")
  private int id;

  @Column(name = "day")
  private String day;

  @Column(name = "startTime")
  private Time startTime;

  @Column(name = "endTime")
  private Time endTime;

  @ManyToOne
  @JoinColumn(name = "doctorUsername")
  private EmployeeEntity doctor;
}
