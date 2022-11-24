package ca.proj.dtos;

import java.sql.Date;

import ca.proj.values.EmployeeRole;
import lombok.Data;

@Data
public class EmployeeDTO {
  private String firstName;
  private String lastName;
  private String gender;
  private Date dateOfBirth;
  private String address;
  private String phone;
  private String email;
  private int salary;
  private EmployeeRole role;
  private String username;
}
