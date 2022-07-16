package com.opcgdb_api.entity;

import com.opcgdb_api.entity.key.AttributeDescriptionKey;
import com.opcgdb_api.entity.key.CardDescriptionKey;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@Accessors(chain = true)
@Entity
@Table(name = "UT_CARD_DESCRIPTION", schema = "public")
@IdClass(CardDescriptionKey.class)
public class CardDescriptionEntity {

    @Id
    @Column(name = "CARD_ID", nullable = false)
    private String cardId;

    @Id
    @Column(name = "LANGUAGE_CODE", nullable = false)
    private String languageCode;

    @Column(name = "NAME")
    private String name;

    @Column(name = "EFFECT")
    private String effect;

}
