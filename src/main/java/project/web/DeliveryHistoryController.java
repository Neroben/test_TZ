package project.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import project.services.DeliveryHistoryService;
import project.services.dto.DeliveryHistoryDto;

import java.util.List;

@RestController
@RequestMapping("api/v1/history")
@RequiredArgsConstructor
public class DeliveryHistoryController {

    private final DeliveryHistoryService deliveryHistoryService;

    @GetMapping
    public List<DeliveryHistoryDto> getAll() {
        return deliveryHistoryService.getAll();
    }

}
