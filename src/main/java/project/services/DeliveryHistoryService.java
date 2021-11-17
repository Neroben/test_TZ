package project.services;

import project.services.dto.DeliveryHistoryDto;

import java.util.List;

public interface DeliveryHistoryService {

    List<DeliveryHistoryDto> getAll();

}
