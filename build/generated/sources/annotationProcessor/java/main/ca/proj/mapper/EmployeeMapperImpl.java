package ca.proj.mapper;

import ca.proj.dtos.EmployeeDTO;
import ca.proj.entity.EmployeeEntity;
import ca.proj.entity.UserEntity;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-11-29T15:54:21-0500",
    comments = "version: 1.4.1.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-7.5.1.jar, environment: Java 11.0.12 (Oracle Corporation)"
)
public class EmployeeMapperImpl implements EmployeeMapper {

    @Override
    public EmployeeDTO toDto(EmployeeEntity employee) {
        if ( employee == null ) {
            return null;
        }

        EmployeeDTO employeeDTO = new EmployeeDTO();

        employeeDTO.setUsername( employeeUserUsername( employee ) );
        employeeDTO.setFirstName( employee.getFirstName() );
        employeeDTO.setLastName( employee.getLastName() );
        employeeDTO.setGender( employee.getGender() );
        employeeDTO.setDateOfBirth( employee.getDateOfBirth() );
        employeeDTO.setAddress( employee.getAddress() );
        employeeDTO.setPhone( employee.getPhone() );
        employeeDTO.setEmail( employee.getEmail() );
        employeeDTO.setSalary( employee.getSalary() );
        employeeDTO.setRole( employee.getRole() );

        return employeeDTO;
    }

    @Override
    public EmployeeEntity toEntity(EmployeeDTO employee) {
        if ( employee == null ) {
            return null;
        }

        EmployeeEntity employeeEntity = new EmployeeEntity();

        employeeEntity.setUser( employeeDTOToUserEntity( employee ) );
        employeeEntity.setFirstName( employee.getFirstName() );
        employeeEntity.setLastName( employee.getLastName() );
        employeeEntity.setGender( employee.getGender() );
        employeeEntity.setDateOfBirth( employee.getDateOfBirth() );
        employeeEntity.setAddress( employee.getAddress() );
        employeeEntity.setPhone( employee.getPhone() );
        employeeEntity.setEmail( employee.getEmail() );
        employeeEntity.setSalary( employee.getSalary() );
        employeeEntity.setRole( employee.getRole() );
        employeeEntity.setUsername( employee.getUsername() );

        return employeeEntity;
    }

    @Override
    public List<EmployeeDTO> toDto(List<EmployeeEntity> employees) {
        if ( employees == null ) {
            return null;
        }

        List<EmployeeDTO> list = new ArrayList<EmployeeDTO>( employees.size() );
        for ( EmployeeEntity employeeEntity : employees ) {
            list.add( toDto( employeeEntity ) );
        }

        return list;
    }

    @Override
    public List<EmployeeEntity> toEntity(List<EmployeeDTO> employees) {
        if ( employees == null ) {
            return null;
        }

        List<EmployeeEntity> list = new ArrayList<EmployeeEntity>( employees.size() );
        for ( EmployeeDTO employeeDTO : employees ) {
            list.add( toEntity( employeeDTO ) );
        }

        return list;
    }

    private String employeeUserUsername(EmployeeEntity employeeEntity) {
        if ( employeeEntity == null ) {
            return null;
        }
        UserEntity user = employeeEntity.getUser();
        if ( user == null ) {
            return null;
        }
        String username = user.getUsername();
        if ( username == null ) {
            return null;
        }
        return username;
    }

    protected UserEntity employeeDTOToUserEntity(EmployeeDTO employeeDTO) {
        if ( employeeDTO == null ) {
            return null;
        }

        UserEntity userEntity = new UserEntity();

        userEntity.setUsername( employeeDTO.getUsername() );

        return userEntity;
    }
}
