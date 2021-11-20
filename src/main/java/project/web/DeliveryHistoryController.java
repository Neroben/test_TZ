package project.web;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import project.services.DeliveryHistoryService;
import project.services.dto.DeliveryHistoryDto;
import project.services.dto.GetDeliveryHistoryDto;

import javax.validation.Valid;
import java.util.List;

import static project.security.configuration.ResourceServerConfiguration.BASE_API;
import static project.web.DeliveryHistoryController.HISTORY_URL;

@RestController
@RequestMapping(BASE_API + HISTORY_URL)
@RequiredArgsConstructor
public class DeliveryHistoryController {

    public static final String HISTORY_URL = "/history";

    private final DeliveryHistoryService deliveryHistoryService;

    @PostMapping
    public List<DeliveryHistoryDto> getAllByPeriod(@RequestBody @Valid GetDeliveryHistoryDto dto) {
        return deliveryHistoryService.getAllByPeriod(dto);
    }

}
