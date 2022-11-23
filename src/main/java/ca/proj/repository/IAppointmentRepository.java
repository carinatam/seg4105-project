package ca.proj.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ca.proj.entity.AppointmentEntity;

@Repository
public interface IAppointmentRepository extends JpaRepository<AppointmentEntity, Integer> {
  
}
