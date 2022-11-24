package ca.proj.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Component;

import ca.proj.dtos.DoctorAvailabilityDTO;
import ca.proj.entity.DoctorAvailabilityEntity;

@Mapper
@Component
public interface DoctorAvailabilityMapper {
  DoctorAvailabilityMapper INSTANCE = Mappers.getMapper(DoctorAvailabilityMapper.class);

  @Mapping(source = "doctor.user.username", target = "doctorUsername")
  DoctorAvailabilityDTO toDto(DoctorAvailabilityEntity doctorAvailability);

  @Mapping(source = "doctorUsername", target = "doctor.user.username")
  DoctorAvailabilityEntity toEntity(DoctorAvailabilityDTO doctorAvailability);

  List<DoctorAvailabilityDTO> toDto(List<DoctorAvailabilityEntity> doctorAvailabilities);

  List<DoctorAvailabilityEntity> toEntity(List<DoctorAvailabilityDTO> doctorAvailabilities);
}
