package com.tarot.tarot.controller;

import com.tarot.tarot.service.TarotService;
import com.tarot.tarot.model.TarotCard;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/tarot")
public class  TarotController {
    @Autowired
    private TarotService tarotService;

//    @GetMapping("/drawThreeCards")
//    public List<TarotCard> drawThreeCards() {
//        return tarotService.drawThreeCards();
//    }
//
//    @GetMapping("/drawSevenCards")
//    public List<TarotCard> drawSevenCards() {
//        return tarotService.drawSevenCards();
//    }
}
