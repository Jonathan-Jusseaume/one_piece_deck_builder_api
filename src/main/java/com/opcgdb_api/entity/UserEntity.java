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
@Table(name = "ut_user", schema = "public")
public class UserEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "mail", nullable = false)
    private String mail;

    @Column(name = "join_date")
    private Date joinDate;

}
