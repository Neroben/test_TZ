package project.services.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import project.persistence.entity.OrderLineEntity;
import project.services.dto.OrderLineDto;

import java.util.UUID;

@Mapper
public interface OrderLineMapper {

    @Mapping(target = "delivery.id", source = "deliveryId")
    OrderLineEntity toEntity(OrderLineDto orderLineDto, UUID deliveryId);

    @Mapping(target = "productName", source = "product.name")
    OrderLineDto toDto(OrderLineEntity entity);

}
