package ca.proj.config.security;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;

import ca.proj.service.LoginUserService;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
  @Autowired
  private DataSource dataSource;

  @Bean
  public UserDetailsService userDetailsService() {
    return new CustomUserDetailsService();
  }

  @Bean
  public LoginUserService loginUserService() {
    return new LoginUserService();
  }

  @Bean
  public PasswordEncoder passwordEncoder() {
    return new PasswordEncoderTest();
  }

  @Bean
  public DaoAuthenticationProvider authenticationProvider() {
    DaoAuthenticationProvider auth = new DaoAuthenticationProvider();
    auth.setUserDetailsService(userDetailsService());
    auth.setPasswordEncoder(passwordEncoder());
    return auth;
  }

  @Override
  protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    auth.authenticationProvider(authenticationProvider());
  }

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http.authorizeRequests()
        .antMatchers("/")
        .permitAll().antMatchers("/receptionist/**").hasAuthority("RECEPTIONIST")
        .antMatchers("/doctor/**").hasAuthority("DOCTOR")
        .antMatchers("/admin/**").hasAuthority("ADMIN")
        .antMatchers("/patient/**").hasAuthority("PATIENT")
        .anyRequest().authenticated().and().formLogin().loginPage("/login")
        .failureUrl("/login-error").defaultSuccessUrl("/loginSuccess", true)
        .usernameParameter("username").passwordParameter("password").permitAll().and()
        .logout().logoutUrl("/logout").invalidateHttpSession(true).logoutSuccessUrl("/").permitAll();

    http.csrf().disable();
    http.headers().frameOptions().disable();
  }
}
