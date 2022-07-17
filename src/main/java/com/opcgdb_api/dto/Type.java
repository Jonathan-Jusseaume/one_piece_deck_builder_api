package com.opcgdb_api.dto;

import com.opcgdb_api.constant.LanguageCodeEnum;
import com.opcgdb_api.entity.TypeDescriptionEntity;
import com.opcgdb_api.entity.TypeEntity;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(chain = true)
public class Type {

    private Long id;

    private String label;

    public Type() {

    }

    public Type(TypeEntity typeEntity, String languageCode) {
        if (!LanguageCodeEnum.languageIsAvailable(languageCode)) {
            languageCode = LanguageCodeEnum.ENGLISH.toString();
        }
        this.id = typeEntity.getId();
        for (TypeDescriptionEntity description : typeEntity.getDescriptions()) {
            if (description.getLanguageCode().equals(languageCode)) {
                this.label = description.getName();
                break;
            }
        }
    }
}
