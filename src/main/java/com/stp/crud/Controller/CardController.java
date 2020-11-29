package com.stp.crud.Controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.stp.crud.model.Card;
import com.stp.crud.model.User;
import com.stp.crud.service.CardService;
import com.stp.crud.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class CardController {

    private final CardService cardService;
    private final UserService userService;

    @Autowired
    public CardController(CardService cardService, ObjectMapper mapper, UserService userService) {
        this.cardService = cardService;
        this.userService = userService;
    }

    @GetMapping("/card-create/{id}")
    public String createCardForm(@PathVariable("id") Long id, Model model){
        User user = userService.findById(id);
        model.addAttribute("card", user);
        return "card-create";
    }

    @RequestMapping(value="/new-card", method= RequestMethod.POST)
    public String createNewCard(@RequestParam("id") Long id, @RequestParam("name") String name){
        User user = userService.findById(id);
        Card card = new Card();
        card.setHeadline(name);
        card.setUser(user);
        cardService.saveCard(card);
        return "redirect:user-info/" + id;
    }

    @GetMapping("/card-update/{id}")
    public String updateCardForm(@PathVariable("id") Long id, Model model){
        Card card = cardService.findById(id);
        model.addAttribute("card", card);
        return "card-update";
    }

    @RequestMapping(value="/update-card", method= RequestMethod.POST)
    public String updateCard(@RequestParam("id") Long id, @RequestParam("name") String name){
        cardService.updCard(name, id);
        return "redirect:user";
    }

    @RequestMapping(value="/del-card", method= RequestMethod.POST)
    public String DelCard(@RequestParam("idCard") Long id){
        cardService.deleteById(id);
        return "redirect:user";
    }


}
