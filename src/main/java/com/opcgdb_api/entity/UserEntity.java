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
@Table(name = "UT_USER", schema = "public")
public class UserEntity {

    @Id
    @Column(name = "MAIL", nullable = false)
    private String mail;

    @Column(name = "JOIN_DATE")
    private Date joinDate;

}
