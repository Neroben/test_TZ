package project.services.dto;

import lombok.Data;

import java.math.BigInteger;
import java.util.UUID;

@Data
public class OrderLineDto {

    private BigInteger price;

    private BigInteger weight;

    private UUID productId;

}
