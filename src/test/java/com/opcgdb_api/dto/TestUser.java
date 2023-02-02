package com.opcgdb_api.dto;


import com.opcgdb_api.entity.UserEntity;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;

import java.sql.Date;
import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;

public class TestUser {

    @Test
    @DisplayName("Test of conversion from UserEntity to User")
    public void testUserEntityToUser() {
        Date currentDate = Date.valueOf(LocalDate.now());
        UserEntity userEntity = new UserEntity()
                .setMail("toto@titi.com")
                .setJoinDate(currentDate)
                .setProfilePicture("http://image.png");

        User user = new User(userEntity);
        assertThat(user.getMail()).isEqualTo("toto@titi.com");
        assertThat(user.getCreationDate()).isEqualTo(currentDate);
        assertThat(user.getProfilePicture()).isEqualTo("http://image.png");
    }

    @Test
    @DisplayName("Test of conversion from User to UserEntity")
    public void testUserToUserEntity() {
        Date currentDate = Date.valueOf(LocalDate.now());
        User user = new User()
                .setMail("toto@titi.com")
                .setCreationDate(currentDate)
                .setProfilePicture("http://image.png");

        UserEntity userEntity = user.toEntity();
        assertThat(userEntity.getMail()).isEqualTo("toto@titi.com");
        assertThat(userEntity.getJoinDate()).isEqualTo(currentDate);
        assertThat(userEntity.getProfilePicture()).isEqualTo("http://image.png");
    }

}
