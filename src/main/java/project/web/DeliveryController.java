package project.web;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import project.services.DeliveryService;
import project.services.dto.AcceptDeliveryDto;

import javax.validation.Valid;
import java.security.Principal;
import java.util.UUID;

import static project.security.configuration.ResourceServerConfiguration.BASE_API;

@RestController
@RequestMapping(BASE_API + DeliveryController.DELIVERY_URL)
@RequiredArgsConstructor
public class DeliveryController {

    public static final String DELIVERY_URL = "/delivery";

    private final DeliveryService deliveryService;

    @PostMapping
    public void acceptDelivery(@RequestBody @Valid AcceptDeliveryDto acceptDeliveryDto, Principal principal) {
        deliveryService.acceptDelivery(acceptDeliveryDto, principal.getName());
    }

    @DeleteMapping("/{id}")
    public void deleteDelivery(@PathVariable("id") UUID id) {
        deliveryService.deleteDelivery(id);
    }

}

