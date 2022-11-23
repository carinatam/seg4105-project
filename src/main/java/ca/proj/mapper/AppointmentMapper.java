package ca.proj.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Component;

import ca.proj.dtos.AppointmentDTO;
import ca.proj.entity.AppointmentEntity;

@Mapper
@Component
public interface AppointmentMapper {
  AppointmentMapper INSTANCE = Mappers.getMapper(AppointmentMapper.class);

  @Mapping(source = "patient.user.username", target = "patientUsername")
  @Mapping(source = "employee.user.username", target = "employeeUsername")
  AppointmentDTO toDto(AppointmentEntity appointment);

  @Mapping(source = "patientUsername", target = "patient.user.username")
  @Mapping(source = "employeeUsername", target = "employee.user.username")
  AppointmentEntity toEntity(AppointmentDTO appointment);

  List<AppointmentDTO> toDto(List<AppointmentEntity> appointments);

  List<AppointmentEntity> toEntity(List<AppointmentDTO> appointments);
}
