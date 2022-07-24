package com.opcgdb_api.model;

import com.opcgdb_api.dto.Color;
import com.opcgdb_api.dto.Rarity;
import com.opcgdb_api.dto.Tag;
import com.opcgdb_api.dto.Type;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.domain.Pageable;

import java.util.Set;

@Getter
@Setter
@ToString
public class CardFilter {
    private Set<Type> types;
    private Set<Color> colors;
    private Set<Tag> tags;
    private Set<Rarity> rarities;
}
