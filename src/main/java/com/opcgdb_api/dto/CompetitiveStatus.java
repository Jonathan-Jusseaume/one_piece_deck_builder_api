package com.opcgdb_api.dto;

import com.opcgdb_api.entity.CompetitiveStatusDescriptionEntity;
import com.opcgdb_api.entity.CompetitiveStatusEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(chain = true)
@NoArgsConstructor
public class CompetitiveStatus {

    private Long id;

    private String label;

    public CompetitiveStatus(CompetitiveStatusEntity competitiveStatusEntity, String languageCode) {
        this.id = competitiveStatusEntity.getId();
        for (CompetitiveStatusDescriptionEntity description : competitiveStatusEntity.getDescriptions()) {
            if (description.getLanguageCode().equals(languageCode)) {
                this.label = description.getName();
                break;
            }
        }
    }
}
