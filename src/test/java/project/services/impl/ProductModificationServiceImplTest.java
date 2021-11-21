package project.services.impl;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import project.persistence.entity.OrderLineEntity;
import project.security.GrantedAuthorityEnum;
import project.services.ProductModificationService;
import project.services.ProductService;
import project.services.dto.ProductInformationDto;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ProductModificationServiceImplTest extends AbstractIntegrationTest {

    @Autowired
    private ProductModificationService productModificationService;

    @Autowired
    private ProductService productService;

    private UUID supplierId;

    @BeforeEach
    void setUp() {
        supplierId = initUserEntity("supplier", GrantedAuthorityEnum.SUPPLIER).getId();
    }

    @AfterEach
    void dropAll() {
        deleteAll();
    }

    @Test
    void saveProduct() {
        productModificationService.saveProduct(initProductInformationDto(), "supplier");
        List<ProductInformationDto> allProducts = productService.getProductsBySupplier(supplierId);
        assertEquals(1, allProducts.size());
        assertEquals("new product", allProducts.get(0).getName());
    }

    private ProductInformationDto initProductInformationDto() {
        ProductInformationDto productInformationDto = new ProductInformationDto();
        productInformationDto.setName("new product");
        productInformationDto.setActualPrice(BigDecimal.valueOf(100L));
        productInformationDto.setSupplierId(supplierId);
        return productInformationDto;
    }

    @Test
    void deleteProduct() {
        productModificationService.saveProduct(initProductInformationDto(), "supplier");
        productModificationService.deleteProduct(productService.getProductsBySupplier(supplierId).get(0).getId(), "supplier");
        List<ProductInformationDto> allProducts = productService.getProductsBySupplier(supplierId);
        assertEquals(0, allProducts.size());
    }
}