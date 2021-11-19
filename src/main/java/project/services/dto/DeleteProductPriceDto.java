package project.services.dto;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class DeleteProductPriceDto {

    private UUID productId;

    private LocalDateTime date;

}
