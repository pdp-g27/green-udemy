package com.udemy.user.entity;

import com.udemy.enums.UserType;
import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "users")
@ToString
@Builder
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String firstName;

    private String lastName;

    private String middleName;

    @Enumerated(EnumType.STRING)
    private UserType userType;

    private String email;

    private String phoneNumber;

    private String password;

}
