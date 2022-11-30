package ca.proj.mapper;

import ca.proj.dtos.PaymentDTO;
import ca.proj.entity.AppointmentEntity;
import ca.proj.entity.PaymentEntity;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
<<<<<<< HEAD
    date = "2022-11-30T10:27:01-0500",
    comments = "version: 1.4.1.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-7.5.1.jar, environment: Java 18.0.2 (Oracle Corporation)"
=======
    date = "2022-11-29T15:54:21-0500",
    comments = "version: 1.4.1.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-7.5.1.jar, environment: Java 11.0.12 (Oracle Corporation)"
>>>>>>> ff25345798fc0e2c61666d215b37ba6774616e65
)
public class PaymentMapperImpl implements PaymentMapper {

    @Override
    public PaymentDTO toDto(PaymentEntity payment) {
        if ( payment == null ) {
            return null;
        }

        PaymentDTO paymentDTO = new PaymentDTO();

        paymentDTO.setAppointmentID( paymentAppointmentAppointmentID( payment ) );
        paymentDTO.setPaymentID( payment.getPaymentID() );
        paymentDTO.setPaymentDate( payment.getPaymentDate() );
        paymentDTO.setPaymentTime( payment.getPaymentTime() );
        paymentDTO.setPaymentAmount( (float) payment.getPaymentAmount() );

        return paymentDTO;
    }

    @Override
    public PaymentEntity toEntity(PaymentDTO payment) {
        if ( payment == null ) {
            return null;
        }

        PaymentEntity paymentEntity = new PaymentEntity();

        paymentEntity.setAppointment( paymentDTOToAppointmentEntity( payment ) );
        paymentEntity.setPaymentID( payment.getPaymentID() );
        paymentEntity.setPaymentDate( payment.getPaymentDate() );
        paymentEntity.setPaymentAmount( payment.getPaymentAmount() );
        paymentEntity.setPaymentTime( payment.getPaymentTime() );

        return paymentEntity;
    }

    @Override
    public List<PaymentDTO> toDto(List<PaymentEntity> payments) {
        if ( payments == null ) {
            return null;
        }

        List<PaymentDTO> list = new ArrayList<PaymentDTO>( payments.size() );
        for ( PaymentEntity paymentEntity : payments ) {
            list.add( toDto( paymentEntity ) );
        }

        return list;
    }

    @Override
    public List<PaymentEntity> toEntity(List<PaymentDTO> payments) {
        if ( payments == null ) {
            return null;
        }

        List<PaymentEntity> list = new ArrayList<PaymentEntity>( payments.size() );
        for ( PaymentDTO paymentDTO : payments ) {
            list.add( toEntity( paymentDTO ) );
        }

        return list;
    }

    private int paymentAppointmentAppointmentID(PaymentEntity paymentEntity) {
        if ( paymentEntity == null ) {
            return 0;
        }
        AppointmentEntity appointment = paymentEntity.getAppointment();
        if ( appointment == null ) {
            return 0;
        }
        int appointmentID = appointment.getAppointmentID();
        return appointmentID;
    }

    protected AppointmentEntity paymentDTOToAppointmentEntity(PaymentDTO paymentDTO) {
        if ( paymentDTO == null ) {
            return null;
        }

        AppointmentEntity appointmentEntity = new AppointmentEntity();

        appointmentEntity.setAppointmentID( paymentDTO.getAppointmentID() );

        return appointmentEntity;
    }
}
