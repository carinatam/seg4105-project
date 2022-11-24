package ca.proj.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Component;

import ca.proj.dtos.EmployeeDTO;
import ca.proj.entity.EmployeeEntity;

@Mapper
@Component
public interface EmployeeMapper {
  EmployeeMapper INSTANCE = Mappers.getMapper(EmployeeMapper.class);

  @Mapping(source = "user.username", target = "username")
  EmployeeDTO toDto(EmployeeEntity employee);

  @Mapping(source = "username", target = "user.username")
  EmployeeEntity toEntity(EmployeeDTO employee);

  List<EmployeeDTO> toDto(List<EmployeeEntity> employees);

  List<EmployeeEntity> toEntity(List<EmployeeDTO> employees);
}
