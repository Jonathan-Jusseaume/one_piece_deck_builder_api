package com.opcgdb_api.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Set;

@Data
@Entity
@Table(name = "UT_TAG")
public class TagEntity {

    @Id
    @Column(name = "ID", nullable = false)
    private Long id;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "TAG_ID")
    private Set<TagDescriptionEntity> descriptions;
}
