package ca.proj.repository;

import java.math.BigInteger;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import ca.proj.entity.PatientEntity;

@Repository
public interface IPatientRepository extends JpaRepository<PatientEntity, Integer> {
  
  @Query(value = "SELECT COUNT(1) FROM patient WHERE username = :username", nativeQuery = true)
  BigInteger isPatient(@Param("username") String username);
}
