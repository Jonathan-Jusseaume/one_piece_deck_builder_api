package com.opcgdb_api.dto;

import com.opcgdb_api.constant.LanguageCodeEnum;
import com.opcgdb_api.entity.AttributeDescriptionEntity;
import com.opcgdb_api.entity.AttributeEntity;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(chain = true)
public class Attribute {

    private Long id;

    private String label;

    public Attribute(AttributeEntity attributeEntity, String languageCode) {
        if (LanguageCodeEnum.languageIsNotAvailable(languageCode)) {
            languageCode = LanguageCodeEnum.ENGLISH.toString();
        }
        this.id = attributeEntity.getId();
        for (AttributeDescriptionEntity description : attributeEntity.getDescriptions()) {
            if (description.getLanguageCode().equals(languageCode)) {
                this.label = description.getName();
                break;
            }
        }
    }
}
