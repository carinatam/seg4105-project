package ca.proj.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Component;

import ca.proj.dtos.UserDTO;
import ca.proj.entity.UserEntity;

@Mapper
@Component
public interface UserMapper {
  UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

  UserDTO toDto(UserEntity user);
  UserEntity toEntity(UserDTO user);
  List<UserDTO> toDto(List<UserEntity> users);
  List<UserEntity> toEntity(List<UserDTO> users);
}
