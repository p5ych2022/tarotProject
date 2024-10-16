package com.tarot.tarot.controller;

import com.tarot.tarot.model.TarotCard;
import com.tarot.tarot.service.TarotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@RestController
@RequestMapping("/tarot")
public class  TarotController {
    @Autowired
    private TarotService tarotService;

    @GetMapping("/draw")
    public List<TarotCard> drawThreeCards() {
        return tarotService.drawThreeCards();
    }
}
