package project.services.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import project.persistence.repository.ProductRepository;
import project.services.ProductModificationService;
import project.services.dto.ProductInformationDto;
import project.services.mapper.ProductMapper;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ProductModificationServiceImpl implements ProductModificationService {

    private final ProductRepository productRepository;

    private final ProductMapper productMapper;

    public void saveProduct(ProductInformationDto productInformationDto) {
        productRepository.save(productMapper.toEntity(productInformationDto));
    }

    public void deleteProduct(UUID id) {
        productRepository.deleteById(id);
    }

}
