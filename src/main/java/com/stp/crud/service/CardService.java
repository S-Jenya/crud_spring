package com.stp.crud.service;

import com.stp.crud.model.Card;
import com.stp.crud.repository.CardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CardService {

    private final CardRepository cardRepository;

    @Autowired
    public CardService(CardRepository cardRepository) {
        this.cardRepository = cardRepository;
    }

    public Card findById(Long id){
        return cardRepository.getOne(id);
    }

    public Card saveCard(Card card){
        return cardRepository.save(card);
    }

    public void deleteById(Long id) {
        cardRepository.deleteById(id);
    }

    public List<Card> selectCardFromUser(Long id){
        return cardRepository.findCardByUserIdCustomQuery(id);
    }

    public List<Card> selectCardByUserId(Long id){
        return cardRepository.findCardCustomQuery(id);
    }

    public void updCard(String name, Long id){
        cardRepository.updCard(name, id);
    }
}
