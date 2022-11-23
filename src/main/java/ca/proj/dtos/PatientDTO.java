package ca.proj.dtos;

import java.sql.Date;

import lombok.Data;

@Data
public class PatientDTO {
  private String firstName;
  private String lastName;
  private String gender;
  private Date dateOfBirth;
  private String address;
  private String phone;
  private String email;
  private String username;
}
