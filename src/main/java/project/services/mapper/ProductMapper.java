package project.services.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import project.persistence.entity.ProductEntity;
import project.services.dto.ProductInformationDto;

import java.util.UUID;

@Mapper
public interface ProductMapper {

    @Mapping(target = "supplier.id", source = "supplier")
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "prices", ignore = true)
    @Mapping(target = "orderLines", ignore = true)
    ProductEntity toEntity(ProductInformationDto dto, UUID supplier);

    @Mapping(target = "supplierId", source = "supplier.id")
    ProductInformationDto toDto(ProductEntity entity);

}
