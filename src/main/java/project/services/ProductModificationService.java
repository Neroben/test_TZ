package project.services;

import project.services.dto.ProductInformationDto;

import java.util.UUID;

public interface ProductModificationService {

    void saveProduct(ProductInformationDto productInformationDto, String email);

    void deleteProduct(UUID id, String email);

}
