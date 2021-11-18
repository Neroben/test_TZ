package project.services.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import project.persistence.repository.ProductPriceRepository;
import project.services.ProductPriceService;
import project.services.dto.ProductPriceDto;

@Service
@RequiredArgsConstructor
public class ProductPriceServiceImpl implements ProductPriceService {

    private final ProductPriceRepository productPriceRepository;

    @Override
    public void createProductPrice(ProductPriceDto priceDto) {
        checkPricePeriod(priceDto);

    }

    private void checkPricePeriod(ProductPriceDto priceDto) {

    }
}
