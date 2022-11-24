package ca.proj.mapper;

import ca.proj.dtos.DoctorAvailabilityDTO;
import ca.proj.entity.DoctorAvailabilityEntity;
import ca.proj.entity.EmployeeEntity;
import ca.proj.entity.UserEntity;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-11-24T13:50:43-0500",
    comments = "version: 1.4.1.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-7.5.1.jar, environment: Java 11.0.12 (Oracle Corporation)"
)
public class DoctorAvailabilityMapperImpl implements DoctorAvailabilityMapper {

    @Override
    public DoctorAvailabilityDTO toDto(DoctorAvailabilityEntity doctorAvailability) {
        if ( doctorAvailability == null ) {
            return null;
        }

        DoctorAvailabilityDTO doctorAvailabilityDTO = new DoctorAvailabilityDTO();

        doctorAvailabilityDTO.setDoctorUsername( doctorAvailabilityDoctorUserUsername( doctorAvailability ) );
        doctorAvailabilityDTO.setDay( doctorAvailability.getDay() );
        doctorAvailabilityDTO.setStartTime( doctorAvailability.getStartTime() );
        doctorAvailabilityDTO.setEndTime( doctorAvailability.getEndTime() );

        return doctorAvailabilityDTO;
    }

    @Override
    public DoctorAvailabilityEntity toEntity(DoctorAvailabilityDTO doctorAvailability) {
        if ( doctorAvailability == null ) {
            return null;
        }

        DoctorAvailabilityEntity doctorAvailabilityEntity = new DoctorAvailabilityEntity();

        doctorAvailabilityEntity.setDoctor( doctorAvailabilityDTOToEmployeeEntity( doctorAvailability ) );
        doctorAvailabilityEntity.setDay( doctorAvailability.getDay() );
        doctorAvailabilityEntity.setStartTime( doctorAvailability.getStartTime() );
        doctorAvailabilityEntity.setEndTime( doctorAvailability.getEndTime() );

        return doctorAvailabilityEntity;
    }

    @Override
    public List<DoctorAvailabilityDTO> toDto(List<DoctorAvailabilityEntity> doctorAvailabilities) {
        if ( doctorAvailabilities == null ) {
            return null;
        }

        List<DoctorAvailabilityDTO> list = new ArrayList<DoctorAvailabilityDTO>( doctorAvailabilities.size() );
        for ( DoctorAvailabilityEntity doctorAvailabilityEntity : doctorAvailabilities ) {
            list.add( toDto( doctorAvailabilityEntity ) );
        }

        return list;
    }

    @Override
    public List<DoctorAvailabilityEntity> toEntity(List<DoctorAvailabilityDTO> doctorAvailabilities) {
        if ( doctorAvailabilities == null ) {
            return null;
        }

        List<DoctorAvailabilityEntity> list = new ArrayList<DoctorAvailabilityEntity>( doctorAvailabilities.size() );
        for ( DoctorAvailabilityDTO doctorAvailabilityDTO : doctorAvailabilities ) {
            list.add( toEntity( doctorAvailabilityDTO ) );
        }

        return list;
    }

    private String doctorAvailabilityDoctorUserUsername(DoctorAvailabilityEntity doctorAvailabilityEntity) {
        if ( doctorAvailabilityEntity == null ) {
            return null;
        }
        EmployeeEntity doctor = doctorAvailabilityEntity.getDoctor();
        if ( doctor == null ) {
            return null;
        }
        UserEntity user = doctor.getUser();
        if ( user == null ) {
            return null;
        }
        String username = user.getUsername();
        if ( username == null ) {
            return null;
        }
        return username;
    }

    protected UserEntity doctorAvailabilityDTOToUserEntity(DoctorAvailabilityDTO doctorAvailabilityDTO) {
        if ( doctorAvailabilityDTO == null ) {
            return null;
        }

        UserEntity userEntity = new UserEntity();

        userEntity.setUsername( doctorAvailabilityDTO.getDoctorUsername() );

        return userEntity;
    }

    protected EmployeeEntity doctorAvailabilityDTOToEmployeeEntity(DoctorAvailabilityDTO doctorAvailabilityDTO) {
        if ( doctorAvailabilityDTO == null ) {
            return null;
        }

        EmployeeEntity employeeEntity = new EmployeeEntity();

        employeeEntity.setUser( doctorAvailabilityDTOToUserEntity( doctorAvailabilityDTO ) );

        return employeeEntity;
    }
}
