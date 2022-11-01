package com.opcgdb_api.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.opcgdb_api.entity.TagDescriptionEntity;
import com.opcgdb_api.entity.TagEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(chain = true)
@JsonDeserialize
@NoArgsConstructor
public class Tag implements Comparable {

    private Long id;

    private String label;

    public Tag(TagEntity tagEntity, String languageCode) {
        this.id = tagEntity.getId();
        for (TagDescriptionEntity description : tagEntity.getDescriptions()) {
            if (description.getLanguageCode().equals(languageCode)) {
                this.label = description.getName();
                break;
            }
        }
    }

    @Override
    public int compareTo(Object o) {
        if (!(o instanceof Tag)) {
            return -1;
        }
        return this.getLabel().compareTo(((Tag) o).getLabel());
    }
}
