package project.services.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import project.errorhandling.exception.ProductNotFoundException;
import project.persistence.entity.ProductEntity;
import project.persistence.entity.ProductPriceEntity;
import project.persistence.repository.ProductPriceRepository;
import project.persistence.repository.ProductRepository;
import project.services.ProductService;
import project.services.dto.ProductInformationDto;
import project.services.mapper.ProductMapper;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    private final ProductPriceRepository productPriceRepository;

    private final ProductMapper productMapper;

    @Override
    public ProductInformationDto getProductInformation(UUID id) {
        ProductEntity product = getProduct(id);
        return mapToProductInformationDto(product);
    }

    private ProductInformationDto mapToProductInformationDto(ProductEntity product) {
        ProductInformationDto productInformationDto = productMapper.toDto(product);
        productInformationDto.setActualPrice(getActualPrice(product).orElse(null));
        return productInformationDto;
    }

    private ProductEntity getProduct(UUID id) {
        return productRepository.findById(id).orElseThrow(() -> new ProductNotFoundException(id.toString()));
    }

    @Override
    public List<ProductInformationDto> getProductsBySupplier(UUID id) {
        return productRepository.findBySupplierId(id).stream()
                .map(this::mapToProductInformationDto).collect(Collectors.toList());
    }

    @Override
    public Optional<BigDecimal> getActualPrice(ProductEntity product) {
        return productPriceRepository.findActualPrice(product.getId(), LocalDateTime.now())
                .map(ProductPriceEntity::getPrice);
    }
}
