package project.services.mapper;

import org.mapstruct.Mapper;
import project.persistence.entity.UserEntity;
import project.services.dto.RegistrationUserDto;
import project.services.dto.SupplierDto;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Mapper
public interface UserMapper {

    UserEntity toEntity(RegistrationUserDto dto);

    SupplierDto toSupplier(UserEntity user);

    default List<SupplierDto> toSuppliers(Collection<UserEntity> userList) {
        return userList.stream().map(this::toSupplier).collect(Collectors.toList());
    }

}
