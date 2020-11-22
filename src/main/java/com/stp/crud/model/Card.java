package com.stp.crud.model;

import lombok.Data;
import javax.persistence.*;

@Data
@Entity
@Table(name = "card")
public class Card {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_card")
    private Long id;

    public String getHeadline() {
        return headline;
    }

    private String headline;

//    @ManyToOne
//    @JoinColumn(name = "id_user", nullable = false)
//    private User user;

    public String toString() {
        return "Id: " + this.id + "; headline: " + this.headline;
    }
}
