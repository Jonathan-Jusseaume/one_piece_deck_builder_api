package com.opcgdb_api.service;

import com.opcgdb_api.dto.Product;
import com.opcgdb_api.repository.ProductDao;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class ProductService {

    private final ProductDao productDao;

    public List<Product> list(String languageCode) {
        return productDao.findAll()
                .stream()
                .map(productEntity -> new Product(productEntity, languageCode))
                .collect(Collectors.toList());
    }

}

