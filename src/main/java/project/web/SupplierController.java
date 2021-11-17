package project.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import project.services.SupplierService;
import project.services.dto.SupplierDto;

import java.util.List;

@RestController
@RequestMapping("api/v1/supplier")
@RequiredArgsConstructor
public class SupplierController {

    private final SupplierService supplierService;

    @GetMapping
    public List<SupplierDto> getAllSupplier() {
        return supplierService.getAllSupplier();
    }

}
