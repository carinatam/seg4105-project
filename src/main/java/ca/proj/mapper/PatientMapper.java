package ca.proj.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Component;

import ca.proj.dtos.PatientDTO;
import ca.proj.entity.PatientEntity;

@Mapper
@Component
public interface PatientMapper {
  PatientMapper INSTANCE = Mappers.getMapper(PatientMapper.class);

  @Mapping(source = "user.username", target = "username")
  PatientDTO toDto(PatientEntity patient);

  @Mapping(source = "username", target = "user.username")
  PatientEntity toEntity(PatientDTO patient);

  List<PatientDTO> toDto(List<PatientEntity> patients);

  List<PatientEntity> toEntity(List<PatientDTO> patients);
}
