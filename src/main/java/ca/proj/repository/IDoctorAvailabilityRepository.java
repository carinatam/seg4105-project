package ca.proj.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ca.proj.entity.DoctorAvailabilityEntity;

@Repository
public interface IDoctorAvailabilityRepository extends JpaRepository<DoctorAvailabilityEntity, Integer> {
  
}