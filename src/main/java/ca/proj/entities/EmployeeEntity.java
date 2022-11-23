package ca.proj.entities;

import java.sql.Date;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "employee")
@Data
public class EmployeeEntity {
  @Column(name = "firstName")
  private String firstName;

  @Column(name = "lastName")
  private String lastName;

  @Column(name = "gender")
  private String gender;

  @Column(name = "dateOfBirth")
  private Date dateOfBirth;

  @Column(name = "address")
  private String address;

  @Column(name = "phone")
  private String phone;

  @Column(name = "email")
  private String email;

  @Column(name = "salary")
  private int salary;

  @Column(name = "role")
  private EmployeeRole role;

  @Id
  private String username;

  @MapsId
  @OneToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "username")
  private UserEntity user;

  @OneToMany(mappedBy = "employee")
  private Set<AppointmentEntity> appointments;

  @OneToMany(mappedBy = "doctor")
  private Set<DoctorAvailabilityEntity> doctorAvailabilities;
}
