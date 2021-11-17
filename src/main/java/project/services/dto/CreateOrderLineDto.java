package project.services.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.math.BigInteger;
import java.util.UUID;

@Data
public class OrderLineDto {

    @NotNull
    private BigInteger price;

    @NotNull
    private BigInteger weight;

    @NotNull
    private UUID productId;

}