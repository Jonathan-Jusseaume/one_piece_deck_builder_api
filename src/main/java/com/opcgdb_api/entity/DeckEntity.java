package com.opcgdb_api.entity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.sql.Date;

@Getter
@Setter
@Accessors(chain = true)
@Entity
@Table(name = "ut_deck", schema = "public")
public class DeckEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "id", nullable = false)
    private String id;

    @Column(name = "user_mail")
    private String userMail;

    @Column(name = "leader_id")
    private String leaderId;

    @Column(name = "name")
    private String name;

    @Column(name = "creation_date")
    private Date creationDate;

    @Column(name = "description")
    private String description;

}
