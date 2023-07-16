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
@Table(name = "UT_COMPETITIVE_STATUS", schema = "public")
public class CompetitiveStatusEntity {

    @Id
    @Column(name = "ID", nullable = false)
    private Long id;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "STATUS_ID")
    private Set<CompetitiveStatusDescriptionEntity> descriptions;

}
