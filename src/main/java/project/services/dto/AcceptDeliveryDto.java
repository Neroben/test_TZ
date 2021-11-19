package project.services.dto;

import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import java.util.List;

@Data
public class AcceptDeliveryDto {

    @NotEmpty
    private List<@Valid CreateOrderLineDto> orderLines;

}
