package ca.proj.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ca.proj.entity.PatientEntity;

@Repository
public interface IPatientRepository extends JpaRepository<PatientEntity, Integer> {
  
}
