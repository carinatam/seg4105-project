package ca.proj.dtos;

import lombok.Data;

@Data
public class LoginUserDTO {
  private String username;
  private String password;
  private String role;
}
