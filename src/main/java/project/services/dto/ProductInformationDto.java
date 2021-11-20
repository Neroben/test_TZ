package project.services.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.math.BigDecimal;
import java.util.UUID;

@Data
public class ProductInformationDto {

    private UUID id;

    @NotBlank
    private String name;

    private UUID supplierId;

    private BigDecimal actualPrice;

}
