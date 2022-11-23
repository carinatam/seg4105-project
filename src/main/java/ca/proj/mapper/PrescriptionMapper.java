package ca.proj.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Component;

import ca.proj.dtos.PrescriptionDTO;
import ca.proj.entity.PrescriptionEntity;

@Mapper
@Component
public interface PrescriptionMapper {
  PrescriptionMapper INSTANCE = Mappers.getMapper(PrescriptionMapper.class);

  @Mapping(source = "appointment.appointmentID", target = "appointmentID")
  PrescriptionDTO toDto(PrescriptionEntity prescription);

  @Mapping(source = "appointmentID", target = "appointment.appointmentID")
  PrescriptionEntity toEntity(PrescriptionDTO prescription);

  List<PrescriptionDTO> toDto(List<PrescriptionEntity> prescriptions);

  List<PrescriptionEntity> toEntity(List<PrescriptionDTO> prescriptions);
}
