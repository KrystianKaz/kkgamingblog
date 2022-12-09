package com.GamingBlog.gamingblog.model;

import com.GamingBlog.gamingblog.model.enums.UserRolesEnum;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(length = 60, unique = true, nullable = false)
    private String email;

    @Column(unique = true, length = 15, nullable = false)
    private String username;

    @Column(length = 80, nullable = false)
    private String password;

    private boolean active = true;

    private LocalTime accountCreationTime;

    private LocalDate accountCreationDate;

    @Column(columnDefinition = "ENUM('ADMIN', 'USER')")
    @Enumerated(EnumType.STRING)
    private UserRolesEnum userRoles = UserRolesEnum.USER;

    @Column(name = "image_name")
    private String file;

}
