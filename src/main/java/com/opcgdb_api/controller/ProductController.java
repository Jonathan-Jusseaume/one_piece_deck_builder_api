package com.opcgdb_api.controller;

import com.opcgdb_api.dto.Color;
import com.opcgdb_api.dto.Product;
import com.opcgdb_api.service.ColorService;
import com.opcgdb_api.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @GetMapping
    public List<Product> list(@RequestParam(name = "language", defaultValue = "en") String languageCode) {
        return productService.list(languageCode);
    }

}
