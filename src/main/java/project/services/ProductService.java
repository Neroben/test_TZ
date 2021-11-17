package project.services;

import project.services.dto.ProductInformationDto;

import java.util.List;
import java.util.UUID;

public interface ProductService {

    ProductInformationDto getProductInformation(UUID id);

    List<ProductInformationDto> getProductsBySupplier(UUID id);

    List<ProductInformationDto> getAllProducts();
}
