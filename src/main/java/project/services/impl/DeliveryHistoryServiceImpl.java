package project.services.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import project.persistence.entity.DeliveryHistoryEntity;
import project.persistence.repository.DeliveryHistoryRepository;
import project.services.DeliveryHistoryService;
import project.services.dto.DeliveryHistoryDto;
import project.services.dto.GetDeliveryHistoryDto;
import project.services.mapper.DeliveryHistoryMapper;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DeliveryHistoryServiceImpl implements DeliveryHistoryService {

    private final DeliveryHistoryRepository deliveryHistoryRepository;

    private final DeliveryHistoryMapper deliveryHistoryMapper;

    @Override
    public List<DeliveryHistoryDto> getAllByPeriod(GetDeliveryHistoryDto dto) {
        List<DeliveryHistoryEntity> deliveryList =
                deliveryHistoryRepository.findAllByPeriod(dto.getDateStart(), dto.getDateEnd());
        return deliveryHistoryMapper.toDtos(deliveryList);
    }

}
