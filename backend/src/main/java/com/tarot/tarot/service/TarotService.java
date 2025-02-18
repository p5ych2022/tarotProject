package com.tarot.tarot.service;

import com.tarot.tarot.model.TarotCard;
import java.util.List;
public interface TarotService {
    List<TarotCard> drawCards(int numberOfCards);


}

