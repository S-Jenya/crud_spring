package com.stp.crud.model;

import javax.persistence.*;
import java.util.Set;

@Entity
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

    public void setName(String name) {
        this.name = name;
    }

    public Set<Card> getCards() {
        return cards;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String toString() {
        return "Id: " + this.id_user + " name: " + this.name + "; password: " + this.password;
    }
}
