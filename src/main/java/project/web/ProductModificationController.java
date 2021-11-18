package project.web;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import project.services.ProductModificationService;
import project.services.ProductPriceService;
import project.services.dto.ProductInformationDto;
import project.services.dto.ProductPriceDto;

import java.security.Principal;
import java.util.UUID;

import static project.security.configuration.ResourceServerConfiguration.BASE_API;
import static project.web.ProductModificationController.PRODUCT_MODIFICATION_URL;

@RestController
@RequestMapping(BASE_API + PRODUCT_MODIFICATION_URL)
@RequiredArgsConstructor
public class ProductModificationController {

    public static final String PRODUCT_MODIFICATION_URL = "/product-mod";

    private final ProductModificationService productModificationService;

    private final ProductPriceService productPriceService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createProduct(@RequestBody ProductInformationDto productDto) {
        productModificationService.saveProduct(productDto);
    }

    @PostMapping("/price")
    @ResponseStatus(HttpStatus.CREATED)
    public void createProductPrice(@RequestBody ProductPriceDto priceDto) {
        productPriceService.createProductPrice(priceDto);
    }

    @PutMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateProduct(@RequestBody ProductInformationDto productDto) {
        productModificationService.saveProduct(productDto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteProduct(@PathVariable UUID id, Principal principal) {
        productModificationService.deleteProduct(id, principal.getName());
    }

}
