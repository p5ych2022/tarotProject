package com.tarot.tarot.DTO;
import com.tarot.tarot.model.TarotCard;
import java.util.List;

public class TarotResponse {
    private List<TarotCard> tarotCards;
    private String interpretation;

    // Constructors, getters, and setters
    public TarotResponse(List<TarotCard> tarotCards, String interpretation) {
        this.tarotCards = tarotCards;
        this.interpretation = interpretation;
    }

    public List<TarotCard> getTarotCards() {
        return tarotCards;
    }

    public void setTarotCards(List<TarotCard> tarotCards) {
        this.tarotCards = tarotCards;
    }

    public String getInterpretation() {
        return interpretation;
    }

    public void setInterpretation(String interpretation) {
        this.interpretation = interpretation;
    }
}
