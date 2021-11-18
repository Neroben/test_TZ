package project.services.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class GetDeliveryHistoryDto {

    private LocalDateTime dateStart;

    private LocalDateTime dateEnd;
}
