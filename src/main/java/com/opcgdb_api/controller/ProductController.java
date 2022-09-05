package com.opcgdb_api.controller;

import com.opcgdb_api.dto.Product;
import com.opcgdb_api.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @Operation(summary = "Get the list of all the different products")
    @GetMapping
    public List<Product> list(
            @Parameter(description = "Code of the language of the response")
            @RequestParam(name = "language", defaultValue = "en") String languageCode) {
        return productService.list(languageCode);
    }

}
