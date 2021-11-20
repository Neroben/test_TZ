package project.services.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class DeleteProductPriceDto {

    @NotNull
    private UUID productId;

    @NotNull
    private LocalDateTime date;

}
