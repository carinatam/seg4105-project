package ca.proj.mapper;

import ca.proj.dtos.UserDTO;
import ca.proj.entity.UserEntity;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-11-24T13:50:43-0500",
    comments = "version: 1.4.1.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-7.5.1.jar, environment: Java 11.0.12 (Oracle Corporation)"
)
public class UserMapperImpl implements UserMapper {

    @Override
    public UserDTO toDto(UserEntity user) {
        if ( user == null ) {
            return null;
        }

        UserDTO userDTO = new UserDTO();

        userDTO.setUsername( user.getUsername() );
        userDTO.setPassword( user.getPassword() );

        return userDTO;
    }

    @Override
    public UserEntity toEntity(UserDTO user) {
        if ( user == null ) {
            return null;
        }

        UserEntity userEntity = new UserEntity();

        userEntity.setUsername( user.getUsername() );
        userEntity.setPassword( user.getPassword() );

        return userEntity;
    }

    @Override
    public List<UserDTO> toDto(List<UserEntity> users) {
        if ( users == null ) {
            return null;
        }

        List<UserDTO> list = new ArrayList<UserDTO>( users.size() );
        for ( UserEntity userEntity : users ) {
            list.add( toDto( userEntity ) );
        }

        return list;
    }

    @Override
    public List<UserEntity> toEntity(List<UserDTO> users) {
        if ( users == null ) {
            return null;
        }

        List<UserEntity> list = new ArrayList<UserEntity>( users.size() );
        for ( UserDTO userDTO : users ) {
            list.add( toEntity( userDTO ) );
        }

        return list;
    }
}
