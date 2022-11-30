package ca.proj.repository;

import java.math.BigInteger;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import ca.proj.entity.PatientEntity;

@Repository
public interface IPatientRepository extends JpaRepository<PatientEntity, String> {
  
  @Query(value = "SELECT COUNT(1) FROM patient WHERE username = :username", nativeQuery = true)
  BigInteger isPatient(@Param("username") String username);

  @Query(value = "SELECT username FROM patient", nativeQuery = true)
  List<String> findAllPatientsUsernames();
}
