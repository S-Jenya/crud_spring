package com.stp.crud.Controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.stp.crud.model.Card;
import com.stp.crud.service.CardService;
import org.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class CardController {

    private final CardService cardService;

    @Autowired
    public CardController(CardService cardService, ObjectMapper mapper) {
        this.cardService = cardService;
    }

    @GetMapping("/card-create")
    public String createCardForm(Card card){
        return "card-create";
    }

    @PostMapping("/new-card")
    public String createNewCard(@ModelAttribute Card card){
        System.out.printf("ff");
        return "card-create";
    }

}
