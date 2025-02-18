package com.tarot.tarot.model;

public class TarotCard {
    private String title;
    private String keywords;
    private String description;
    private String explaination;

    public TarotCard() {
    }



    public String getTitle() {
        return title;
    }

    public String getKeywords() {
        return keywords;
    }

    public String getDescription() {
        return description;
    }

    public String getExplaination() {
        return explaination;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setExplaination(String explaination) {
        this.explaination = explaination;
    }


    @Override
    public String toString() {
        // return "TarotCard{title='" + title + "', keywords='" + keywords + "',  description='" + description +  "', explaination='" + explaination + "}";
        //return "TarotCard{title='" + title + "', keywords='" + keywords + "}";
        return "TarotCard{title='" + title + "}";
    }



//    public String toString_zhipu() {
//        return "TarotCard{title='" + title + "', keywords='" + keywords + "'explaination='" + explaination + "}";
//    }
}