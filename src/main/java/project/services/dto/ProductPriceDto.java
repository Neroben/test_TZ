package project.services.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class ProductPriceDto {

    private BigDecimal price;

    private LocalDateTime dateStart;

    private LocalDateTime dateEnd;

}
