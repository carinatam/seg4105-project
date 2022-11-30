package ca.proj.repository;

import java.math.BigInteger;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import ca.proj.entity.EmployeeEntity;

@Repository
public interface IEmployeeRepository extends JpaRepository<EmployeeEntity, String> {
  
  @Query(value = "SELECT COUNT(1) FROM employee WHERE username = :username AND role = 'Receptionist'", nativeQuery = true)
  BigInteger isReceptionist(@Param("username") String username);

  @Query(value = "SELECT COUNT(1) FROM employee WHERE username = :username AND role = 'Doctor'", nativeQuery = true)
  BigInteger isDoctor(@Param("username") String username);

  @Query(value = "SELECT username FROM employee WHERE role = 'Doctor'", nativeQuery = true)
  List<String> findAllDoctorsUsernames();

  @Query(value = "SELECT * FROM employee WHERE role != 'Admin'", nativeQuery = true)
  List<EmployeeEntity> findAllNoAdmin();
}
