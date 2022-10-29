package com.opcgdb_api.dto;

import com.opcgdb_api.entity.AttributeDescriptionEntity;
import com.opcgdb_api.entity.AttributeEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(chain = true)
@NoArgsConstructor
public class Attribute {

    private Long id;

    private String label;

    public Attribute(AttributeEntity attributeEntity, String languageCode) {
        this.id = attributeEntity.getId();
        for (AttributeDescriptionEntity description : attributeEntity.getDescriptions()) {
            if (description.getLanguageCode().equals(languageCode)) {
                this.label = description.getName();
                break;
            }
        }
    }
}
