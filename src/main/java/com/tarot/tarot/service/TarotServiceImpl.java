package com.tarot.tarot.service;

import com.tarot.tarot.model.TarotCard;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
@Service
public class TarotServiceImpl implements TarotService {
    private static final Map<Integer, TarotCard> cards = new ConcurrentHashMap<>();

    static {
        // init cards
        cards.put(1, new TarotCard("The Fool", "About new beginnings, ..."));
        cards.put(2, new TarotCard("The Magician", "About action, ..."));
        cards.put(3, new TarotCard("The High Priestess", "About intuition, ..."));
        cards.put(4, new TarotCard("The Empress", "About fertility, ..."));
        cards.put(5, new TarotCard("The Emperor", "About authority, ..."));
        cards.put(6, new TarotCard("The Hierophant", "About tradition, ..."));
        cards.put(7, new TarotCard("The Lovers", "About relationships, ..."));
        cards.put(8, new TarotCard("The Chariot", "About control, ..."));
        cards.put(9, new TarotCard("Strength", "About courage, ..."));
        cards.put(10, new TarotCard("The Hermit", "About introspection, ..."));
        cards.put(11, new TarotCard("Wheel of Fortune", "About destiny, ..."));
        cards.put(12, new TarotCard("Justice", "About fairness, ..."));
        cards.put(13, new TarotCard("The Hanged", "About sacrifice, ..."));
        cards.put(14, new TarotCard("Death", "About endings, ..."));
        cards.put(15, new TarotCard("Temperance", "About balance, ..."));
        cards.put(16, new TarotCard("The Devil", "About materialism, ..."));
    }

    @Override
    public List<TarotCard> drawThreeCards() {
        List<TarotCard> deck = new ArrayList<>(cards.values());
        Collections.shuffle(deck);
        return deck.subList(0, 3);
    }
}
