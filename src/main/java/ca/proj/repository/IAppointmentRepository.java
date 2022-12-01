package ca.proj.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import ca.proj.entity.AppointmentEntity;

@Repository
public interface IAppointmentRepository extends JpaRepository<AppointmentEntity, Integer> {

  @Query(value = "SELECT * FROM appointment WHERE employeeusername = :username", nativeQuery = true)
  List<AppointmentEntity> findAllByEmployeeUsername(@Param("username") String username);

  @Query(value = "SELECT * FROM appointment WHERE patientusername = :username", nativeQuery = true)
  List<AppointmentEntity> findAllByPatientUsername(@Param("username") String username);

  @Query(value = "SELECT patientusername FROM appointment WHERE employeeusername = :username", nativeQuery = true)
  List<String> findPatientsByDoctorUsername(@Param("username") String username);

  @Query(value = "SELECT * FROM appointment WHERE employeeusername = :username", nativeQuery = true)
  List<AppointmentEntity> findAllByDoctorUsername(@Param("username") String username);

  @Query(value = "SELECT appointmentid FROM appointment WHERE patientusername = :username", nativeQuery = true)
  List<Integer> findAllIdsByPatientUsername(@Param("username") String username);
  
}
