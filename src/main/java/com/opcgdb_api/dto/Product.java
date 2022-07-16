package com.opcgdb_api.dto;

import com.opcgdb_api.constant.LanguageCodeEnum;
import com.opcgdb_api.entity.ProductDescriptionEntity;
import com.opcgdb_api.entity.ProductEntity;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.Date;

@Getter
@Setter
@Accessors(chain = true)
public class Product {

    private String id;

    private Date releaseDate;

    private String label;

    public Product(ProductEntity productEntity, String languageCode) {
        if (!LanguageCodeEnum.languageIsAvailable(languageCode)) {
            languageCode = LanguageCodeEnum.ENGLISH.toString();
        }
        this.id = productEntity.getId();
        this.releaseDate = productEntity.getReleaseDate();
        for (ProductDescriptionEntity description : productEntity.getDescriptions()) {
            if (description.getLanguageCode().equals(languageCode)) {
                this.label = description.getName();
                break;
            }
        }
    }
}
