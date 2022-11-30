package ca.proj.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import ca.proj.entity.PaymentEntity;

@Repository
public interface IPaymentRepository extends JpaRepository<PaymentEntity, Integer> {
  
  @Query(value = "SELECT * FROM payment WHERE appointmentid = :id", nativeQuery = true)
  List<PaymentEntity> findAllByAppointmentId(int id);
}
