package com.opcgdb_api.entity;

import com.opcgdb_api.entity.key.CardColorKey;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@Accessors(chain = true)
@Entity
@Table(name = "UT_CARD_COLOR", schema = "public")
@IdClass(CardColorKey.class)
public class CardColorEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "COLOR_ID", nullable = false)
    private Long colorId;

    @Id
    @Column(name = "CARD_ID", nullable = false)
    private String cardId;

}
