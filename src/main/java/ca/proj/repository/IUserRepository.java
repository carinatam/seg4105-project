package ca.proj.repository;

import java.math.BigInteger;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import ca.proj.entity.UserEntity;

@Repository
public interface IUserRepository extends JpaRepository<UserEntity, String> {
  
  @Query(value = "SELECT COUNT(1) FROM users WHERE username = :newUsername", nativeQuery = true)
  BigInteger exists(@Param("newUsername") String newUsername);

  @Query(value = "SELECT COUNT(1) FROM users WHERE username = :name", nativeQuery = true)
  BigInteger loginExists(@Param("name") String name);

  @Query(value = "SELECT password FROM users WHERE username = :name", nativeQuery = true)
  String getPassword(@Param("name") String name);
}
