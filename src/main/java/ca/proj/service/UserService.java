package ca.proj.service;

import java.math.BigInteger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ca.proj.dtos.UserDTO;
import ca.proj.entity.UserEntity;
import ca.proj.mapper.UserMapper;
import ca.proj.repository.IUserRepository;

@Service
public class UserService {
  
  @Autowired private IUserRepository userRepository;

  public boolean userExists(String username) {
    return userRepository.exists(username) == BigInteger.ONE;
  }

  public UserDTO createUser(UserDTO user) {
    UserEntity newUser = UserMapper.INSTANCE.toEntity(user);
    userRepository.save(newUser);
    return user;
  }
}
