package ca.proj.mapper;

import ca.proj.dtos.AppointmentDTO;
import ca.proj.entity.AppointmentEntity;
import ca.proj.entity.EmployeeEntity;
import ca.proj.entity.PatientEntity;
import ca.proj.entity.UserEntity;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-11-23T12:36:15-0500",
    comments = "version: 1.4.1.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-7.5.1.jar, environment: Java 18.0.2 (Oracle Corporation)"
)
public class AppointmentMapperImpl implements AppointmentMapper {

    @Override
    public AppointmentDTO toDto(AppointmentEntity appointment) {
        if ( appointment == null ) {
            return null;
        }

        AppointmentDTO appointmentDTO = new AppointmentDTO();

        appointmentDTO.setPatientUsername( appointmentPatientUserUsername( appointment ) );
        appointmentDTO.setEmployeeUsername( appointmentEmployeeUserUsername( appointment ) );
        appointmentDTO.setAppointmentID( appointment.getAppointmentID() );
        appointmentDTO.setAppointmentDate( appointment.getAppointmentDate() );
        appointmentDTO.setAppointmentTime( appointment.getAppointmentTime() );

        return appointmentDTO;
    }

    @Override
    public AppointmentEntity toEntity(AppointmentDTO appointment) {
        if ( appointment == null ) {
            return null;
        }

        AppointmentEntity appointmentEntity = new AppointmentEntity();

        appointmentEntity.setPatient( appointmentDTOToPatientEntity( appointment ) );
        appointmentEntity.setEmployee( appointmentDTOToEmployeeEntity( appointment ) );
        appointmentEntity.setAppointmentID( appointment.getAppointmentID() );
        appointmentEntity.setAppointmentDate( appointment.getAppointmentDate() );
        appointmentEntity.setAppointmentTime( appointment.getAppointmentTime() );

        return appointmentEntity;
    }

    @Override
    public List<AppointmentDTO> toDto(List<AppointmentEntity> appointments) {
        if ( appointments == null ) {
            return null;
        }

        List<AppointmentDTO> list = new ArrayList<AppointmentDTO>( appointments.size() );
        for ( AppointmentEntity appointmentEntity : appointments ) {
            list.add( toDto( appointmentEntity ) );
        }

        return list;
    }

    @Override
    public List<AppointmentEntity> toEntity(List<AppointmentDTO> appointments) {
        if ( appointments == null ) {
            return null;
        }

        List<AppointmentEntity> list = new ArrayList<AppointmentEntity>( appointments.size() );
        for ( AppointmentDTO appointmentDTO : appointments ) {
            list.add( toEntity( appointmentDTO ) );
        }

        return list;
    }

    private String appointmentPatientUserUsername(AppointmentEntity appointmentEntity) {
        if ( appointmentEntity == null ) {
            return null;
        }
        PatientEntity patient = appointmentEntity.getPatient();
        if ( patient == null ) {
            return null;
        }
        UserEntity user = patient.getUser();
        if ( user == null ) {
            return null;
        }
        String username = user.getUsername();
        if ( username == null ) {
            return null;
        }
        return username;
    }

    private String appointmentEmployeeUserUsername(AppointmentEntity appointmentEntity) {
        if ( appointmentEntity == null ) {
            return null;
        }
        EmployeeEntity employee = appointmentEntity.getEmployee();
        if ( employee == null ) {
            return null;
        }
        UserEntity user = employee.getUser();
        if ( user == null ) {
            return null;
        }
        String username = user.getUsername();
        if ( username == null ) {
            return null;
        }
        return username;
    }

    protected UserEntity appointmentDTOToUserEntity(AppointmentDTO appointmentDTO) {
        if ( appointmentDTO == null ) {
            return null;
        }

        UserEntity userEntity = new UserEntity();

        userEntity.setUsername( appointmentDTO.getPatientUsername() );

        return userEntity;
    }

    protected PatientEntity appointmentDTOToPatientEntity(AppointmentDTO appointmentDTO) {
        if ( appointmentDTO == null ) {
            return null;
        }

        PatientEntity patientEntity = new PatientEntity();

        patientEntity.setUser( appointmentDTOToUserEntity( appointmentDTO ) );

        return patientEntity;
    }

    protected UserEntity appointmentDTOToUserEntity1(AppointmentDTO appointmentDTO) {
        if ( appointmentDTO == null ) {
            return null;
        }

        UserEntity userEntity = new UserEntity();

        userEntity.setUsername( appointmentDTO.getEmployeeUsername() );

        return userEntity;
    }

    protected EmployeeEntity appointmentDTOToEmployeeEntity(AppointmentDTO appointmentDTO) {
        if ( appointmentDTO == null ) {
            return null;
        }

        EmployeeEntity employeeEntity = new EmployeeEntity();

        employeeEntity.setUser( appointmentDTOToUserEntity1( appointmentDTO ) );

        return employeeEntity;
    }
}
