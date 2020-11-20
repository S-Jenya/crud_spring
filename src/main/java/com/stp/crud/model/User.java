package com.stp.crud.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_user;
    @Column(
            name = "name",
            unique = true
    )
    private String name;
    @Column(name = "password")
    private String password;
}
