package com.tarot.tarot.service;

import com.tarot.tarot.model.TarotCard;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.io.InputStream;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import com.fasterxml.jackson.core.type.TypeReference;
import java.util.Collections;
import java.security.SecureRandom;

@Service
public class TarotServiceImpl implements TarotService {
    public static final Map<Integer, TarotCard> cards = new ConcurrentHashMap<>();

    static {
        // init cards
        try {
            // 使用类加载器获取资源输入流
            InputStream is = TarotServiceImpl.class.getClassLoader().getResourceAsStream("static/tarot_cards.json");
            if (is == null) {
                throw new RuntimeException("Resource file tarot_cards.json not found in static folder");
            }
            ObjectMapper mapper = new ObjectMapper();

            Map<Integer, TarotCard> loadedCards = mapper.readValue(is, new TypeReference<Map<Integer, TarotCard>>() {});
            cards.putAll(loadedCards);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to load tarot cards from JSON", e);
        }

    }

//    @Override
//    public List<TarotCard> drawThreeCards() {
//        List<TarotCard> deck = new ArrayList<>(cards.values());
//        // Collections.shuffle(deck);
//        SecureRandom random = new SecureRandom();
//        Collections.shuffle(deck, random);
//        return deck.subList(0, 3);
//    }
//
//    public List<TarotCard> drawSevenCards() {
//        List<TarotCard> deck = new ArrayList<>(cards.values());
//        // Collections.shuffle(deck);
//        SecureRandom random = new SecureRandom();
//        Collections.shuffle(deck, random);
//        return deck.subList(0,7);
//    }

//    @Override
//    public List<TarotCard> drawCards(int numberOfCards) {
//        List<TarotCard> deck = new ArrayList<>(cards.values());
//        // Collections.shuffle(deck);
//        SecureRandom random = new SecureRandom();
//        Collections.shuffle(deck, random);
//        return deck.subList(0,numberOfCards);
//    }

    @Override
    public List<TarotCard> drawCards(int numberOfCards) {
        // 假设 JSON 中的牌顺序是：正位、逆位、正位、逆位...
        List<TarotCard> deck = new ArrayList<>(cards.values());
        SecureRandom random = new SecureRandom();

        // 将相邻的正位和逆位分为一组
        List<List<TarotCard>> groupedCards = new ArrayList<>();
        for (int i = 0; i < deck.size(); i += 2) {
            // 每组包含正位和逆位的两张牌
            List<TarotCard> group = new ArrayList<>();
            group.add(deck.get(i));
            if (i + 1 < deck.size()) {
                group.add(deck.get(i + 1));
            }
            groupedCards.add(group);
        }

        // 随机选择 numberOfCards 组
        Collections.shuffle(groupedCards, random);
        List<TarotCard> drawnCards = new ArrayList<>();

        for (int i = 0; i < numberOfCards; i++) {
            List<TarotCard> selectedGroup = groupedCards.get(i);
            // 从每组中随机选择正位或逆位
            TarotCard drawnCard = selectedGroup.get(random.nextInt(selectedGroup.size()));
            drawnCards.add(drawnCard);
        }

        return drawnCards;
    }


}
