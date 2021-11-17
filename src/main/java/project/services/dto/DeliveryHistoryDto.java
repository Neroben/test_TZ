package project.services.dto;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Data
public class DeliveryHistoryDto {

    private UUID consumerId;

    private UUID supplierId;

    private LocalDateTime date;

    private List<OrderLineDto> orderLines;
}
