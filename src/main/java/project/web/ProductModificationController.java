package project.web;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import project.services.ProductModificationService;
import project.services.ProductPriceService;
import project.services.dto.DeleteProductPriceDto;
import project.services.dto.ProductInformationDto;
import project.services.dto.ProductPriceDto;

import javax.validation.Valid;
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
    public void createProduct(@RequestBody @Valid ProductInformationDto productDto, Principal principal) {
        productModificationService.saveProduct(productDto, principal.getName());
    }

    @PostMapping("/price")
    @ResponseStatus(HttpStatus.CREATED)
    public void createProductPrice(@RequestBody @Valid ProductPriceDto priceDto, Principal principal) {
        productPriceService.createProductPrice(priceDto, principal.getName());
    }

    @DeleteMapping("/price")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteProductPrice(@RequestBody @Valid DeleteProductPriceDto priceDto, Principal principal) {
        productPriceService.deleteProductPrice(priceDto, principal.getName());
    }


    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteProduct(@PathVariable("id") UUID id, Principal principal) {
        productModificationService.deleteProduct(id, principal.getName());
    }

}
