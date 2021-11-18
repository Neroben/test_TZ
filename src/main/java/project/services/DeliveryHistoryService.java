package project.services;

import project.services.dto.DeliveryHistoryDto;
import project.services.dto.GetDeliveryHistoryDto;

import java.util.List;

public interface DeliveryHistoryService {

    List<DeliveryHistoryDto> getAllByPeriod(GetDeliveryHistoryDto dto);

}
