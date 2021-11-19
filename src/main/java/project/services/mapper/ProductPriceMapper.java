package project.services.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import project.persistence.entity.ProductPriceEntity;
import project.services.dto.ProductPriceDto;

@Mapper
public interface ProductPriceMapper {

    @Mapping(target = "product.id", source = "productId")
    ProductPriceEntity toEntity(ProductPriceDto dto);

}
