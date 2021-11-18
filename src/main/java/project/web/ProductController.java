package project.web;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import project.services.ProductService;
import project.services.dto.ProductInformationDto;

import java.util.List;
import java.util.UUID;

import static project.security.configuration.ResourceServerConfiguration.BASE_API;
import static project.web.ProductController.PRODUCT_URL;

@RestController
@RequestMapping(BASE_API + PRODUCT_URL)
@RequiredArgsConstructor
public class ProductController {

    public static final String PRODUCT_URL = "/product";

    private final ProductService productService;

    @GetMapping("/{id}")
    public ProductInformationDto getProduct(@PathVariable UUID id) {
        return productService.getProductInformation(id);
    }

    @GetMapping("/supplier-{id}")
    public List<ProductInformationDto> getProductBySupplier(@PathVariable UUID id) {
        return productService.getProductsBySupplier(id);
    }

    @GetMapping
    public List<ProductInformationDto> getAllProduct() {
        return productService.getAllProducts();
    }
}
