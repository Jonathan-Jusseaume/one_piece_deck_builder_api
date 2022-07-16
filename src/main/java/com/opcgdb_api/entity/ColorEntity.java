package com.opcgdb_api.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.util.Set;

@Getter
@Setter
@Accessors(chain = true)
@Entity
@Table(name = "UT_COLOR", schema = "public")
public class ColorEntity {

    @Id
    @Column(name = "ID", nullable = false)
    private Long id;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "COLOR_ID")
    private Set<ColorDescriptionEntity> descriptions;

}
