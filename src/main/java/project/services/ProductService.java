package project.services;

import project.persistence.entity.ProductEntity;
import project.services.dto.ProductInformationDto;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ProductService {

    ProductInformationDto getProductInformation(UUID id);

    List<ProductInformationDto> getProductsBySupplier(UUID id);

    List<ProductInformationDto> getAllProducts();

    Optional<BigDecimal> getActualPrice(ProductEntity product);
}
