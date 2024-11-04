package com.hust.smart_apartment.entity;

import com.hust.smart_apartment.constants.Role;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "users")
@Accessors(chain = true)
public class User extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    @Column(name = "username",nullable = false,unique = true)
    private String username;

    @Column(name = "fullName",nullable = false)
    private String fullName;

    @Column(name = "password",nullable = false)
    private String password;

    @Column(name = "email",nullable = false,unique = true)
    private String email;

    @Column(name = "role",nullable = false)
    private Role role;

    @Column(name = "residentId")
    private Long residentId;

    private Boolean isVerified = false;
}
