package project.services.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import project.persistence.entity.ProductEntity;
import project.services.dto.ProductInformationDto;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Mapper
public interface ProductMapper {

    @Mapping(target = "supplier.id", source = "supplierId")
    ProductEntity toEntity(ProductInformationDto dto);

    @Mapping(target = "supplierId", source = "supplier.id")
    ProductInformationDto toDto(ProductEntity entity);

    default List<ProductInformationDto> toDtos(Collection<ProductEntity> entities){
        return entities.stream().map(this::toDto).collect(Collectors.toList());
    }

}
