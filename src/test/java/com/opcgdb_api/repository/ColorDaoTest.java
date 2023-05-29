package com.opcgdb_api.repository;

import com.opcgdb_api.dto.Color;
import com.opcgdb_api.entity.ColorEntity;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;
import java.util.Optional;

import static org.springframework.test.context.jdbc.Sql.ExecutionPhase.AFTER_TEST_METHOD;
import static org.springframework.test.context.jdbc.Sql.ExecutionPhase.BEFORE_TEST_METHOD;

@DataJpaTest
@Sql(scripts = "/db-tests/colors/create-color.sql", executionPhase = BEFORE_TEST_METHOD)
@Sql(scripts = "/db-tests/colors/cleanup-color.sql", executionPhase = AFTER_TEST_METHOD)
class ColorDaoTest {

    @Autowired
    private ColorDao colorDao;

    @Test
    @DisplayName("List of all colors in database")
    void testFindAll() {
        List<ColorEntity> colorEntities = this.colorDao.findAll();
        Assertions.assertEquals(6, colorEntities.size());
    }

    @Test
    @DisplayName("Search a Color By ID")
    void testFindByIdExists() {
        Optional<ColorEntity> optionalColor = this.colorDao.findById(0L);
        Assertions.assertTrue(optionalColor.isPresent());
        ColorEntity colorEntity = optionalColor.get();
        Assertions.assertEquals(0L, colorEntity.getId());
        Color color = new Color(colorEntity, "en");
        Assertions.assertEquals("Red", color.getLabel());
    }

    @Test
    @DisplayName("Search a Color By ID that doesn't exists")
    void testFindByIdNotExists() {
        Optional<ColorEntity> optionalColor = this.colorDao.findById(20L);
        Assertions.assertTrue(optionalColor.isEmpty());
    }
}
