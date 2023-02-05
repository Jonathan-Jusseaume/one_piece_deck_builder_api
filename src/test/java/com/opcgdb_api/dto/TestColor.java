package com.opcgdb_api.dto;


import com.opcgdb_api.entity.ColorDescriptionEntity;
import com.opcgdb_api.entity.ColorEntity;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;

import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

public class TestColor {

    @Test
    @DisplayName("Test of conversion from ColorEntity to Color")
    public void testColorEntityToAttribute() {
        ColorEntity colorEntity = new ColorEntity()
                .setId(1L)
                .setDescriptions(Set.of(
                        new ColorDescriptionEntity()
                                .setName("Red")
                                .setLanguageCode("en"),
                        new ColorDescriptionEntity()
                                .setName("Rouge")
                                .setLanguageCode("fr")
                ));

        Color color = new Color(colorEntity, "en");
        assertThat(color.getId()).isEqualTo(1L);
        assertThat(color.getLabel()).isEqualTo("Red");
    }

}
