package com.opcgdb_api.dto;

import com.opcgdb_api.entity.UserEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.sql.Date;

@Getter
@Setter
@Accessors(chain = true)
@NoArgsConstructor
public class User {

    private String mail;

    private Date creationDate;

    private String profilePicture;

    public User(UserEntity userEntity) {
        this.setMail(userEntity.getMail());
        this.setCreationDate(userEntity.getJoinDate());
        this.setProfilePicture(userEntity.getProfilePicture());
    }

    public UserEntity toEntity() {
        return new UserEntity()
                .setJoinDate(creationDate)
                .setMail(mail)
                .setProfilePicture(profilePicture);
    }
}
