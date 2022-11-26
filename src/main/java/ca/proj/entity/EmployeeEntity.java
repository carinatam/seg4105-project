package ca.proj.entity;

import java.sql.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import ca.proj.values.EmployeeRole;
import lombok.Data;

@Entity
@Table(name = "employee")
@Data
public class EmployeeEntity {
  @Column(name = "firstname")
  private String firstName;

  @Column(name = "lastname")
  private String lastName;

  @Column(name = "gender")
  private String gender;

  @Column(name = "dateofbirth")
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
  @Enumerated(EnumType.STRING)
  private EmployeeRole role;

  @Id
  @Column(name = "username")
  private String username;

  @PrimaryKeyJoinColumn
  @OneToOne(fetch = FetchType.LAZY, optional = false, cascade = CascadeType.ALL)
  private UserEntity user;

  @OneToMany(mappedBy = "employee")
  private Set<AppointmentEntity> appointments;

  @OneToMany(mappedBy = "doctor")
  private Set<DoctorAvailabilityEntity> doctorAvailabilities;
}
