package project.services.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import project.persistence.entity.ProductEntity;
import project.persistence.repository.ProductRepository;
import project.services.ProductService;
import project.services.dto.ProductInformationDto;
import project.services.mapper.ProductMapper;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    private final ProductMapper productMapper;

    @Override
    public ProductInformationDto getProductInformation(UUID id) {
        return productMapper.toDto(getProduct(id));
    }

    private ProductEntity getProduct(UUID id) {
        return productRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Product is not found"));
    }

    @Override
    public List<ProductInformationDto> getProductsBySupplier(UUID id) {
        return productMapper.toDtos(productRepository.findBySupplierId(id));
    }

    @Override
    public List<ProductInformationDto> getAllProducts() {
        return productMapper.toDtos(productRepository.findAll());
    }
}
