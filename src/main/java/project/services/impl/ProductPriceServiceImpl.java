package project.services.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import project.errorhandling.exception.PricePeriodException;
import project.errorhandling.exception.ProductNotFoundException;
import project.errorhandling.exception.ProductPriceNotFoundException;
import project.persistence.entity.ProductEntity;
import project.persistence.entity.ProductPriceEntity;
import project.persistence.repository.ProductPriceRepository;
import project.persistence.repository.ProductRepository;
import project.services.ProductPriceService;
import project.services.dto.DeleteProductPriceDto;
import project.services.dto.ProductPriceDto;
import project.services.mapper.ProductPriceMapper;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class ProductPriceServiceImpl implements ProductPriceService {

    private final ProductPriceRepository productPriceRepository;

    private final ProductRepository productRepository;

    private final ProductPriceMapper productPriceMapper;

    @Override
    public void createProductPrice(ProductPriceDto priceDto, String email) {
        checkPricePeriod(priceDto, email);
        productPriceRepository.save(productPriceMapper.toEntity(priceDto));
    }

    private void checkPricePeriod(ProductPriceDto priceDto, String email) {
        ProductEntity product = productRepository.findByIdAndUserEmail(priceDto.getProductId(), email)
                .orElseThrow(() -> new ProductNotFoundException(priceDto.getPrice().toString()));
        checkActualDate(product, priceDto.getDateStart());
        checkActualDate(product, priceDto.getDateEnd());
    }

    private void checkActualDate(ProductEntity product, LocalDateTime date) {
        productPriceRepository.findActualPrice(product.getId(), date).ifPresent(a -> {
            throw new PricePeriodException(a.getPrice());
        });
    }

    @Override
    public void deleteProductPrice(DeleteProductPriceDto dto, String email) {
        ProductPriceEntity actualPrice = productPriceRepository.findActualPrice(dto.getProductId(), dto.getDate())
                .orElseThrow(() -> new ProductPriceNotFoundException(dto.getProductId().toString()));
        productPriceRepository.deleteByIdAndUserEmail(actualPrice.getId(), email);
    }
}
