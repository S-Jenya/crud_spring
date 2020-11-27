package com.stp.crud.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import java.util.Set;

@Entity
@ToString(exclude = "cards")
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = false)
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_user")
    private Long id_user;
    @Column(
            name = "name",
            unique = true
    )
    private String name;

    public Long getId_user() {
        return id_user;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    @Column(name = "password")
    private String password;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private Set<Card> cards;

    public String toString() {
        return "Id: " + this.id_user + " name: " + this.name + "; password: " + this.password;
    }
}
