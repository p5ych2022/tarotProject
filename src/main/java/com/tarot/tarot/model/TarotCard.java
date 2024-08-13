package com.tarot.tarot.model;



public class TarotCard {
    private String name;
    private String orientation;
    private String description;

    public TarotCard() {
    }

    public TarotCard(String name,String orientation, String description) {
        this.name = name;
        this.orientation = orientation;
        this.description = description;
    }

    public String getName() {
        return name;
    }
    public String getOrientation() {
        return orientation;
    }
    public String getDescription() {
        return description;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setOrientation(String orientation) {
        this.orientation = orientation;
    }
    public void setDescription(String description) {
        this.description = description;
    }
}