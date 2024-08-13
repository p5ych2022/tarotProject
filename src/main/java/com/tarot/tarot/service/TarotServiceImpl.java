package com.tarot.tarot.service;

import com.tarot.tarot.model.TarotCard;
import com.tarot.tarot.repository.TarotCardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@Service
public class TarotServiceImpl implements TarotService {
    @Autowired
    private TarotCardRepository tarotCardRepository;

    @Override
    public List<TarotCard> drawThreeCards() {
        List<TarotCard> allCards = tarotCardRepository.findAll();
        Random rand = new Random();
        return rand.ints(0, allCards.size())
                .distinct()
                .limit(3)
                .mapToObj(allCards::get)
                .collect(Collectors.toList());
    }
}
