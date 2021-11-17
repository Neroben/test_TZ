package project.services.dto;

import lombok.Data;

import java.util.UUID;

@Data
public class ProductInformationDto {

    private UUID id;

    private String name;

    private UUID supplierId;

}
