package com.opcgdb_api.model;

import com.opcgdb_api.dto.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Set;

@Getter
@Setter
@ToString
public class CardFilter {
    private String keyword;
    private Set<Product> products;
    private Set<Type> types;
    private Set<Color> colors;
    private Set<Tag> tags;
    private Set<Rarity> rarities;
    private Set<Integer> costs;
    private Set<Integer> powers;
}
