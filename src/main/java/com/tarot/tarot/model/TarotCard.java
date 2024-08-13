package com.tarot.tarot.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class TarotCard {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String description;

    // Constructors, getters, and setters
    public TarotCard() {
    }

    public TarotCard(String name, String description) {
        this.name = name;
        this.description = description;
    }

    // standard getters and setters
}