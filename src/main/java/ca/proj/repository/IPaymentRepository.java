package ca.proj.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ca.proj.entity.PaymentEntity;

@Repository
public interface IPaymentRepository extends JpaRepository<PaymentEntity, Integer> {
  
}
