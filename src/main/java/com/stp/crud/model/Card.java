package com.stp.crud.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Data
@Entity
@ToString(exclude = "institutions")
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = false)
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

    public Long getId_card() {
        return id_card;
    }

    public String toString() {
        return "Id: " + this.id_card + "; headline: " + this.headline;
    }
}
