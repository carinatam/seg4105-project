package ca.proj.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Component;

import ca.proj.dtos.PaymentDTO;
import ca.proj.entity.PaymentEntity;

@Mapper
@Component
public interface PaymentMapper {
  PaymentMapper INSTANCE = Mappers.getMapper(PaymentMapper.class);

  @Mapping(source = "appointment.appointmentID", target = "appointmentID")
  PaymentDTO toDto(PaymentEntity payment);

  @Mapping(source = "appointmentID", target = "appointment.appointmentID")
  PaymentEntity toEntity(PaymentDTO payment);

  List<PaymentDTO> toDto(List<PaymentEntity> payments);

  List<PaymentEntity> toEntity(List<PaymentDTO> payments);
}
