package com.opcgdb_api.controller;

import com.opcgdb_api.config.LanguageResolver;
import com.opcgdb_api.dto.Product;
import com.opcgdb_api.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    private final LanguageResolver languageResolver;

    @Operation(summary = "Get the list of all the different products")
    @GetMapping
    public List<Product> list(HttpServletRequest request) {
        return productService.list(languageResolver.resolveLocale(request).getLanguage());
    }

}
