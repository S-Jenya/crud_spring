package com.stp.crud.service;

import com.stp.crud.model.Card;
import com.stp.crud.repository.CardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
//@EnableJpaRepositories("com.stp.crud.repository")
public class CardService {

    private final CardRepository cardRepository;

    @Autowired
    public CardService(CardRepository cardRepository) {
        this.cardRepository = cardRepository;
    }

    public Card findById(Long id){
        return cardRepository.getOne(id);
    }

    public List<Card> findAll(){
        return cardRepository.findAll();
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
}
