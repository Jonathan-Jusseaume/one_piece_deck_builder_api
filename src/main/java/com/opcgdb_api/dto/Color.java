package com.opcgdb_api.dto;

import com.opcgdb_api.constant.LanguageCodeEnum;
import com.opcgdb_api.entity.ColorDescriptionEntity;
import com.opcgdb_api.entity.ColorEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(chain = true)
@NoArgsConstructor
public class Color {

    private Long id;

    private String label;

    public Color(ColorEntity colorEntity, String languageCode) {
        if (LanguageCodeEnum.languageIsNotAvailable(languageCode)) {
            languageCode = LanguageCodeEnum.ENGLISH.toString();
        }
        this.id = colorEntity.getId();
        for (ColorDescriptionEntity description : colorEntity.getDescriptions()) {
            if (description.getLanguageCode().equals(languageCode)) {
                this.label = description.getName();
                break;
            }
        }
    }
}
