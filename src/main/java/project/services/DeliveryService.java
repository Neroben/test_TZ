package project.services;

import project.services.dto.AcceptDeliveryDto;

import java.util.UUID;

public interface DeliveryService {

    void acceptDelivery(AcceptDeliveryDto acceptDeliveryDto);

    void updateDelivery(AcceptDeliveryDto acceptDeliveryDto);

    void deleteDelivery(UUID id);

}
