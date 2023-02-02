package com.opcgdb_api.dto;


import com.opcgdb_api.entity.AttributeDescriptionEntity;
import com.opcgdb_api.entity.AttributeEntity;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;

import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

public class TestAttribute {

    @Test
    @DisplayName("Test of conversion from AttributeEntity to Attribute")
    public void testAttributeEntityToAttribute() {
        AttributeEntity attributeEntity = new AttributeEntity()
                .setId(1L)
                .setDescriptions(Set.of(
                        new AttributeDescriptionEntity()
                                .setName("Slasher")
                                .setLanguageCode("en"),
                        new AttributeDescriptionEntity()
                                .setName("Sabreur")
                                .setLanguageCode("fr")
                ));

        Attribute attribute = new Attribute(attributeEntity, "fr");
        assertThat(attribute.getId()).isEqualTo(1L);
        assertThat(attribute.getLabel()).isEqualTo("Sabreur");
    }

}
