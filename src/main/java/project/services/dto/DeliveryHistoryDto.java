package project.services.dto;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class DeliveryHistoryDto {

    private String consumerEmail;

    private String supplierEmail;

    private LocalDateTime date;

    private List<OrderLineDto> orderLines;
}
