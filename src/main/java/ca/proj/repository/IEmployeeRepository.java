package ca.proj.repository;

import java.math.BigInteger;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import ca.proj.entity.EmployeeEntity;

@Repository
public interface IEmployeeRepository extends JpaRepository<EmployeeEntity, Integer> {
  
  @Query(value = "SELECT COUNT(1) FROM employee WHERE username = :username AND role = 'Receptionist'", nativeQuery = true)
  BigInteger isReceptionist(@Param("username") String username);

  @Query(value = "SELECT COUNT(1) FROM employee WHERE username = :username AND role = 'Doctor'", nativeQuery = true)
  BigInteger isDoctor(@Param("username") String username);
}
