package ca.proj.mapper;

import ca.proj.dtos.PrescriptionDTO;
import ca.proj.entity.AppointmentEntity;
import ca.proj.entity.PrescriptionEntity;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-11-26T17:32:14-0500",
    comments = "version: 1.4.1.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-7.5.1.jar, environment: Java 11.0.12 (Oracle Corporation)"
)
public class PrescriptionMapperImpl implements PrescriptionMapper {

    @Override
    public PrescriptionDTO toDto(PrescriptionEntity prescription) {
        if ( prescription == null ) {
            return null;
        }

        PrescriptionDTO prescriptionDTO = new PrescriptionDTO();

        prescriptionDTO.setAppointmentID( prescriptionAppointmentAppointmentID( prescription ) );
        prescriptionDTO.setPrescriptionID( prescription.getPrescriptionID() );
        prescriptionDTO.setPrescriptionDate( prescription.getPrescriptionDate() );
        prescriptionDTO.setPrescription( prescription.getPrescription() );

        return prescriptionDTO;
    }

    @Override
    public PrescriptionEntity toEntity(PrescriptionDTO prescription) {
        if ( prescription == null ) {
            return null;
        }

        PrescriptionEntity prescriptionEntity = new PrescriptionEntity();

        prescriptionEntity.setAppointment( prescriptionDTOToAppointmentEntity( prescription ) );
        prescriptionEntity.setPrescriptionID( prescription.getPrescriptionID() );
        prescriptionEntity.setPrescriptionDate( prescription.getPrescriptionDate() );
        prescriptionEntity.setPrescription( prescription.getPrescription() );

        return prescriptionEntity;
    }

    @Override
    public List<PrescriptionDTO> toDto(List<PrescriptionEntity> prescriptions) {
        if ( prescriptions == null ) {
            return null;
        }

        List<PrescriptionDTO> list = new ArrayList<PrescriptionDTO>( prescriptions.size() );
        for ( PrescriptionEntity prescriptionEntity : prescriptions ) {
            list.add( toDto( prescriptionEntity ) );
        }

        return list;
    }

    @Override
    public List<PrescriptionEntity> toEntity(List<PrescriptionDTO> prescriptions) {
        if ( prescriptions == null ) {
            return null;
        }

        List<PrescriptionEntity> list = new ArrayList<PrescriptionEntity>( prescriptions.size() );
        for ( PrescriptionDTO prescriptionDTO : prescriptions ) {
            list.add( toEntity( prescriptionDTO ) );
        }

        return list;
    }

    private int prescriptionAppointmentAppointmentID(PrescriptionEntity prescriptionEntity) {
        if ( prescriptionEntity == null ) {
            return 0;
        }
        AppointmentEntity appointment = prescriptionEntity.getAppointment();
        if ( appointment == null ) {
            return 0;
        }
        int appointmentID = appointment.getAppointmentID();
        return appointmentID;
    }

    protected AppointmentEntity prescriptionDTOToAppointmentEntity(PrescriptionDTO prescriptionDTO) {
        if ( prescriptionDTO == null ) {
            return null;
        }

        AppointmentEntity appointmentEntity = new AppointmentEntity();

        appointmentEntity.setAppointmentID( prescriptionDTO.getAppointmentID() );

        return appointmentEntity;
    }
}
