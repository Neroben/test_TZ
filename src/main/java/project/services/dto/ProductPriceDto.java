package project.services.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class ProductPriceDto {

    private BigDecimal price;

    private LocalDateTime dateStart;

    private LocalDateTime dateEnd;

    private UUID productId;

}
