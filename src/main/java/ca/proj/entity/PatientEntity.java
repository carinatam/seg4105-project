package ca.proj.entity;

import java.sql.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "patient")
@Data
public class PatientEntity {
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

  @Id
  @Column(name = "username")
  private String username;

  @PrimaryKeyJoinColumn
  @OneToOne(fetch = FetchType.LAZY, optional = false, cascade = CascadeType.ALL)
  private UserEntity user;

  @OneToMany(mappedBy = "patient", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
  private Set<AppointmentEntity> appointments;

}
