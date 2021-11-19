package project.services;

import project.services.dto.DeleteProductPriceDto;
import project.services.dto.ProductPriceDto;

public interface ProductPriceService {

    void createProductPrice(ProductPriceDto priceDto, String email);

    void deleteProductPrice(DeleteProductPriceDto dto, String email);
}
