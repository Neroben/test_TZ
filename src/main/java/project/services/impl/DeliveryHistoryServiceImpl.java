package project.services.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import project.persistence.entity.DeliveryHistoryEntity;
import project.persistence.repository.DeliveryHistoryRepository;
import project.services.DeliveryHistoryService;
import project.services.dto.DeliveryHistoryDto;
import project.services.mapper.DeliveryHistoryMapper;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DeliveryHistoryServiceImpl implements DeliveryHistoryService {

    private final DeliveryHistoryRepository deliveryHistoryRepository;

    private final DeliveryHistoryMapper deliveryHistoryMapper;

    public List<DeliveryHistoryDto> getAll() {
        List<DeliveryHistoryEntity> deliveryList = deliveryHistoryRepository.findAll();
        List<DeliveryHistoryDto> deliveryHistoryDtos = deliveryHistoryMapper.toDtos(deliveryList);
        return deliveryHistoryDtos;
    }

}
