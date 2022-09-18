package com.opcgdb_api.dto;

import com.opcgdb_api.entity.TypeDescriptionEntity;
import com.opcgdb_api.entity.TypeEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(chain = true)
@NoArgsConstructor
public class Type {

    private Long id;

    private String label;

    public Type(TypeEntity typeEntity, String languageCode) {
        this.id = typeEntity.getId();
        for (TypeDescriptionEntity description : typeEntity.getDescriptions()) {
            if (description.getLanguageCode().equals(languageCode)) {
                this.label = description.getName();
                break;
            }
        }
    }
}
