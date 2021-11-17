package project.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import project.services.DeliveryService;
import project.services.dto.AcceptDeliveryDto;

import java.util.UUID;

@RestController
@RequestMapping("api/v1/delivery")
@RequiredArgsConstructor
public class DeliveryController {

    private final DeliveryService deliveryService;

    @PostMapping
    public void acceptDelivery(@RequestBody AcceptDeliveryDto acceptDeliveryDto) {
        deliveryService.acceptDelivery(acceptDeliveryDto);
    }

    @PutMapping
    public void updateDelivery(@RequestBody AcceptDeliveryDto acceptDeliveryDto) {
        deliveryService.updateDelivery(acceptDeliveryDto);
    }

    @DeleteMapping
    public void deleteDelivery(UUID id) {
        deliveryService.deleteDelivery(id);
    }

}

