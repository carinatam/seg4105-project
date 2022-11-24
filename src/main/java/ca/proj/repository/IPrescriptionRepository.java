package ca.proj.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ca.proj.entity.PrescriptionEntity;

@Repository
public interface IPrescriptionRepository extends JpaRepository<PrescriptionEntity, Integer> {
  
}
