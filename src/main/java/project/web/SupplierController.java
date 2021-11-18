package project.web;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import project.services.SupplierService;
import project.services.dto.SupplierDto;

import java.util.List;

import static project.security.configuration.ResourceServerConfiguration.BASE_API;
import static project.web.SupplierController.SUPPLIER_URL;

@RestController
@RequestMapping(BASE_API + SUPPLIER_URL)
@RequiredArgsConstructor
public class SupplierController {

    public static final String SUPPLIER_URL = "/supplier";

    private final SupplierService supplierService;

    @GetMapping
    public List<SupplierDto> getAllSupplier() {
        return supplierService.getAllSupplier();
    }

}
