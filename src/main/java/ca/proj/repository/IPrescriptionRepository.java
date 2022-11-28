package ca.proj.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import ca.proj.entity.PrescriptionEntity;

@Repository
public interface IPrescriptionRepository extends JpaRepository<PrescriptionEntity, Integer> {
  
  @Query(value = "SELECT * FROM prescription WHERE appointmentid = :id", nativeQuery = true)
  List<PrescriptionEntity> findAllByAppointmentId(int id);

}
