package project.services;

import project.services.dto.AcceptDeliveryDto;

import java.util.UUID;

public interface DeliveryService {

    void acceptDelivery(AcceptDeliveryDto acceptDeliveryDto, String email);

    void deleteDelivery(UUID id, String email);

}
