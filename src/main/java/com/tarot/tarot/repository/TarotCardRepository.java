package com.tarot.tarot.repository;

import com.tarot.tarot.model.TarotCard;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TarotCardRepository extends JpaRepository<TarotCard, Long> {
}