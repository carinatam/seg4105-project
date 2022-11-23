package ca.proj.config.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import ca.proj.dtos.LoginUserDTO;
import ca.proj.service.LoginUserService;

public class CustomUserDetailsService implements UserDetailsService {
  @Autowired
  private LoginUserService service;

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    LoginUserDTO user = service.getUser(username);
    if(user == null) {
      throw new UsernameNotFoundException("User not found");
    }
    return new CustomUserDetails(user);
  }
}
