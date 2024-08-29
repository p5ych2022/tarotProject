package com.tarot.tarot.model;



public class TarotCard {
    private String title;
    private String explain;
    private int negative;
    private String work;
    private String love;
    private String friend;
    private String affection;

    public TarotCard() {
    }

    public TarotCard(String name,String orientation, String description) {
        this.title = title;
        this.explain = explain;
        this.negative = negative;
        this.work = work;
        this.love = love;
        this.friend = friend;
        this.affection = affection;
    }

    public String getTitle() {
        return title;
    }
    public String getExplain() {
        return explain;
    }
    public int getnegative() {
        return negative;
    }

    public String getWork() {
        return work;
    }

    public String getLove() {
        return love;
    }

    public String getFriend() {
        return friend;
    }

    public String getAffection() {
        return affection;
    }

    public void setTitle(String title) {
        this.title = title;
    }
    public void setExplain(String explain) {
        this.explain = explain;
    }
    public void setNegative(int negative) {
        this.negative = negative;
    }
    public void setWork(String work) {
        this.work = work;
    }
    public void setLove(String love) {
        this.love = love;
    }
    public void setFriend(String friend) {
        this.friend = friend;
    }
    public void setAffection(String affection) {
        this.affection = affection;
    }
}