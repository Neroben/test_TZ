package project.services.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import project.persistence.repository.DeliveryHistoryRepository;
import project.services.DeliveryService;
import project.services.dto.AcceptDeliveryDto;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class DeliveryServiceImpl implements DeliveryService {

    private final DeliveryHistoryRepository deliveryRepository;

    public void acceptDelivery(AcceptDeliveryDto acceptDeliveryDto) {
    }

    public void updateDelivery(AcceptDeliveryDto acceptDeliveryDto) {

    }

    public void deleteDelivery(UUID id) {

    }
}
