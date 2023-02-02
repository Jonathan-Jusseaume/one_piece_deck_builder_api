package com.opcgdb_api.dto;

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
        this.setId(colorEntity.getId());
        this.setLabel(colorEntity.getDescriptions()
                .stream()
                .filter(description -> description.getLanguageCode().equals(languageCode))
                .map(ColorDescriptionEntity::getName)
                .findFirst()
                .orElse(""));
    }
}
