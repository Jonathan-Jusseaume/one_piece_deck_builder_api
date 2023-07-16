package com.opcgdb_api.entity.key;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@EqualsAndHashCode
@Getter
@Setter
public class CompetitiveStatusDescriptionKey implements Serializable {

    private Long statusId;

    private String languageCode;
}
