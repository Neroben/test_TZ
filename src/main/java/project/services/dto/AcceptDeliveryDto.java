package project.services.dto;

import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import java.util.List;
import java.util.UUID;

@Data
public class AcceptDeliveryDto {

    private UUID id;

    @NotEmpty
    private List<@Valid CreateOrderLineDto> orderLines;

}
