package project.services.mapper;

import org.mapstruct.Mapper;
import project.persistence.entity.DeliveryHistoryEntity;
import project.services.dto.AcceptDeliveryDto;
import project.services.dto.DeliveryHistoryDto;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Mapper
public interface DeliveryHistoryMapper {

    DeliveryHistoryEntity toEntity(AcceptDeliveryDto acceptDeliveryDto);

    DeliveryHistoryDto toDto(DeliveryHistoryEntity entity);

    default List<DeliveryHistoryDto> toDtos(Collection<DeliveryHistoryEntity> entities) {
        return entities.stream().map(this::toDto).collect(Collectors.toList());
    }

}
