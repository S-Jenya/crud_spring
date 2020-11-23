package com.stp.crud.model;

import lombok.Data;
import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Data
@Entity
@Table(name = "card")
public class Institution {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "institution_id")
    private Long id;

    public void setCards(Set<Card> cards) {
        this.cards = cards;
    }

    public Set<Card> getCards() {
        return cards;
    }

    private String name;

    public void setName(String name) {
        this.name = name;
    }

    @ManyToMany(mappedBy = "institutions")
    private Set<Card> cards;

    public Institution() {    }
    public Institution(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public String toString() {
        return "Id: " + this.id + ";name: " + this.name;
    }
}
