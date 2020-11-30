package com.stp.crud.model;


import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "card")
public class Card {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_card")
    private Long id_card;

    public String getHeadline() {
        return headline;
    }

    private String headline;

    @ManyToOne
    @JoinColumn(name = "id_user", nullable = false)
    private User user;

    @ManyToMany(cascade = { CascadeType.ALL })
    @JoinTable(
            name = "Card_institution",
            joinColumns = { @JoinColumn(name = "card_id") },
            inverseJoinColumns = { @JoinColumn(name = "institution_id") }
    )
    private List<Institution> institutions;

    public List<Institution> getInstitutions() {
        return institutions;
    }

    public void setInstitutions(List<Institution> institutions) {
        this.institutions = institutions;
    }

    public void setId_card(Long id_card) {
        this.id_card = id_card;
    }

    public Long getId_card() {
        return id_card;
    }

    public void setHeadline(String headline) {
        this.headline = headline;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }


    public void setIdNyll() {
        this.id_card = null;
    }

    public String toString() {
        return "Id: " + this.id_card + "; headline: " + this.headline;
    }
}
