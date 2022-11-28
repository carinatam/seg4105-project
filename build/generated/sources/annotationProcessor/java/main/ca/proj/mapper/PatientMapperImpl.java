package ca.proj.mapper;

import ca.proj.dtos.PatientDTO;
import ca.proj.entity.PatientEntity;
import ca.proj.entity.UserEntity;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-11-27T19:01:21-0500",
    comments = "version: 1.4.1.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-7.5.1.jar, environment: Java 11.0.12 (Oracle Corporation)"
)
public class PatientMapperImpl implements PatientMapper {

    @Override
    public PatientDTO toDto(PatientEntity patient) {
        if ( patient == null ) {
            return null;
        }

        PatientDTO patientDTO = new PatientDTO();

        patientDTO.setUsername( patientUserUsername( patient ) );
        patientDTO.setFirstName( patient.getFirstName() );
        patientDTO.setLastName( patient.getLastName() );
        patientDTO.setGender( patient.getGender() );
        patientDTO.setDateOfBirth( patient.getDateOfBirth() );
        patientDTO.setAddress( patient.getAddress() );
        patientDTO.setPhone( patient.getPhone() );
        patientDTO.setEmail( patient.getEmail() );

        return patientDTO;
    }

    @Override
    public PatientEntity toEntity(PatientDTO patient) {
        if ( patient == null ) {
            return null;
        }

        PatientEntity patientEntity = new PatientEntity();

        patientEntity.setUser( patientDTOToUserEntity( patient ) );
        patientEntity.setFirstName( patient.getFirstName() );
        patientEntity.setLastName( patient.getLastName() );
        patientEntity.setGender( patient.getGender() );
        patientEntity.setDateOfBirth( patient.getDateOfBirth() );
        patientEntity.setAddress( patient.getAddress() );
        patientEntity.setPhone( patient.getPhone() );
        patientEntity.setEmail( patient.getEmail() );
        patientEntity.setUsername( patient.getUsername() );

        return patientEntity;
    }

    @Override
    public List<PatientDTO> toDto(List<PatientEntity> patients) {
        if ( patients == null ) {
            return null;
        }

        List<PatientDTO> list = new ArrayList<PatientDTO>( patients.size() );
        for ( PatientEntity patientEntity : patients ) {
            list.add( toDto( patientEntity ) );
        }

        return list;
    }

    @Override
    public List<PatientEntity> toEntity(List<PatientDTO> patients) {
        if ( patients == null ) {
            return null;
        }

        List<PatientEntity> list = new ArrayList<PatientEntity>( patients.size() );
        for ( PatientDTO patientDTO : patients ) {
            list.add( toEntity( patientDTO ) );
        }

        return list;
    }

    private String patientUserUsername(PatientEntity patientEntity) {
        if ( patientEntity == null ) {
            return null;
        }
        UserEntity user = patientEntity.getUser();
        if ( user == null ) {
            return null;
        }
        String username = user.getUsername();
        if ( username == null ) {
            return null;
        }
        return username;
    }

    protected UserEntity patientDTOToUserEntity(PatientDTO patientDTO) {
        if ( patientDTO == null ) {
            return null;
        }

        UserEntity userEntity = new UserEntity();

        userEntity.setUsername( patientDTO.getUsername() );

        return userEntity;
    }
}
