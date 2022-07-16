package com.opcgdb_api.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Date;

@Getter
@Setter
@Accessors(chain = true)
@Entity
@Table(name = "UT_DECK", schema = "public")
public class DeckEntity {

    @Id
    @Column(name = "ID", nullable = false)
    private String id;

    @Column(name = "USER_MAIL")
    private String userMail;

    @Column(name = "LEADER_ID")
    private String leaderId;

    @Column(name = "NAME")
    private String name;

    @Column(name = "CREATION_DATE")
    private Date creationDate;

    @Column(name = "DESCRIPTION")
    private String description;

}
