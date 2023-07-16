package com.opcgdb_api.dto;

import com.opcgdb_api.entity.ProductDescriptionEntity;
import com.opcgdb_api.entity.ProductEntity;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.Date;

@Getter
@Setter
@Accessors(chain = true)
@NoArgsConstructor
@EqualsAndHashCode
public class Product implements Comparable<Product> {

    private String id;

    private Date releaseDate;

    private String label;

    public Product(ProductEntity productEntity, String languageCode) {
        this.id = productEntity.getId();
        this.releaseDate = productEntity.getReleaseDate();
        for (ProductDescriptionEntity description : productEntity.getDescriptions()) {
            if (description.getLanguageCode().equals(languageCode)) {
                this.label = description.getName();
                break;
            }
        }
    }

    @Override
    public int compareTo(Product product) {
        return this.id.compareTo(product.getId());
    }
}
