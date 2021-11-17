package project.web;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import project.services.DeliveryService;
import project.services.dto.AcceptDeliveryDto;

import java.security.Principal;
import java.util.UUID;

@RestController
@RequestMapping("api/v1/delivery")
@RequiredArgsConstructor
public class DeliveryController {

    private final DeliveryService deliveryService;

    @PostMapping
    public void acceptDelivery(@RequestBody AcceptDeliveryDto acceptDeliveryDto, Principal principal) {
        deliveryService.acceptDelivery(acceptDeliveryDto, principal.getName());
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

